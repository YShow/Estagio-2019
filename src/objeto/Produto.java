package objeto;


public class Produto {
    private int codigo;
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCodigo() {
        return codigo;
    }
    private Boolean ativo;
    private Double preco;
    private int quantidade;
    private String nome;
}
