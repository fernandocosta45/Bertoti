package br.com.exemplo;

public class Medicamento {
    private String nome;
    private String fabricante;
    private double preco;
    private int quantidade;

    public Medicamento(String nome, String fabricante, double preco, int quantidade) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Medicamento(String nome, String fabricante) {
        this(nome, fabricante, 0.0, 0);
    }

    public String getNome() { return nome; }
    public String getFabricante() { return fabricante; }
    public double getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }

    public void reduzirEstoque(int qtd) {
        if (qtd <= 0) {
            System.out.println("Quantidade inválida!");
            return;
        }

        if (qtd <= quantidade) {
            quantidade -= qtd;
        } else {
            System.out.println("Estoque insuficiente para " + nome);
        }
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                " | Fabricante: " + fabricante +
                " | Preço: R$" + preco +
                " | Estoque: " + quantidade;
    }
}