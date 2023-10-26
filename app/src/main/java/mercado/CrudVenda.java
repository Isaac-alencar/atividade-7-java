package mercado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudVenda {
  Connection connection;

  CrudVenda(Connection connection) {
    this.connection = new DatabaseConnection().connection;
  }

  public void cadastrar(int produtoId, int quantidade) {
    try {
      String sqlVerificarEstoque = "SELECT quantidade FROM Produto WHERE id = ?";
      PreparedStatement verificarEstoque = connection.prepareStatement(sqlVerificarEstoque);
      verificarEstoque.setInt(1, produtoId);

      String sqlInserirVenda = "INSERT INTO Venda (data_venda, quantidade, produto_id) VALUES (NOW(), ?, ?)";
      PreparedStatement inserirVenda = connection.prepareStatement(sqlInserirVenda);
      inserirVenda.setInt(1, quantidade);
      inserirVenda.setInt(2, produtoId);

      String sqlAtualizarEstoque = "UPDATE Produto SET quantidade = quantidade - ? WHERE id = ?";
      PreparedStatement atualizarEstoque = connection.prepareStatement(sqlAtualizarEstoque);
      atualizarEstoque.setInt(1, quantidade);
      atualizarEstoque.setInt(2, produtoId);

      connection.commit();
    } catch (SQLException e) {

      e.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }
    }
  }

  public void cancelar(int vendaId) {

    try {
      String sqlObterProdutoVenda = "SELECT produto_id, quantidade FROM Venda WHERE id = ?";
      PreparedStatement obterProdutoVenda = connection.prepareStatement(sqlObterProdutoVenda);
      obterProdutoVenda.setInt(1, vendaId);

      ResultSet resultSet = obterProdutoVenda.executeQuery();

      if (resultSet.next()) {
        int produtoIdDaVenda = resultSet.getInt("produto_id");
        int quantidadeDaVenda = resultSet.getInt("quantidade");

        String sqlExcluirVenda = "DELETE FROM Venda WHERE id = ?";
        PreparedStatement excluirVenda = connection.prepareStatement(sqlExcluirVenda);
        excluirVenda.setInt(1, vendaId);

        String sqlAtualizarEstoque = "UPDATE Produto SET quantidade = quantidade + ? WHERE id = ?";
        PreparedStatement atualizarEstoque = connection.prepareStatement(sqlAtualizarEstoque);
        atualizarEstoque.setInt(1, quantidadeDaVenda);
        atualizarEstoque.setInt(2, produtoIdDaVenda);

        connection.commit();
      }
    } catch (SQLException e) {

      e.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }
    }
  }

}
