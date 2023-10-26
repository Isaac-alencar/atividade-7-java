package mercado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudProduto {

  Connection connection;

  CrudProduto(Connection connection) {
    this.connection = new DatabaseConnection().connection;
  }

  public void inserir(String nome, Double preco, int quantidade) {
    try {

      String sql = "INSERT INTO your_product_table (name, price, quantity) VALUES (?, ?, ?)";
      PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
      preparedStatement.setString(1, nome);
      preparedStatement.setDouble(2, preco);
      preparedStatement.setInt(3, quantidade);

      int rowsInserted = preparedStatement.executeUpdate();
      if (rowsInserted > 0) {
        System.out.println("Product created successfully.");
      } else {
        System.out.println("Failed to create the product.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void buscar() {
    try {
      String sql = "SELECT * FROM mercado";
      PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        double preco = resultSet.getDouble("preco");
        int quantidade = resultSet.getInt("quantidade");
        System.out.println("ID: " + id + ", Nome: " + nome + ", Preço: " + preco + ", Quantidade: " + quantidade);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void update (int id, String novoNome, Double novoPreco, int novaQuantidade) {
    try {

      String sql = "UPDATE your_product_table SET name = ?, price = ?, quantity = ? WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, novoNome);
      preparedStatement.setDouble(2, novoPreco);
      preparedStatement.setInt(3, novaQuantidade);
      preparedStatement.setInt(4, id);

      int rowsUpdated = preparedStatement.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("Product updated successfully.");
      } else {
        System.out.println("No product found with the given ID.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void excluir(int id) {
    try {

      String sql = "DELETE FROM your_product_table WHERE id = ?";
      PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);

      int rowsDeleted = preparedStatement.executeUpdate();
      if (rowsDeleted > 0) {
        System.out.println("Product deleted successfully.");
      } else {
        System.out.println("No product found with the given ID.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
