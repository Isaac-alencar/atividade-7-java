package mercado;

public class Produto {
  protected Integer id;
  public String nome;
  public Double preco;
  public Integer quantidade;

  public Integer getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public Double getPreco() {
    return preco;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

}
