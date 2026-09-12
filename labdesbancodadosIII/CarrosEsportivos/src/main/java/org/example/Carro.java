package org.example;

public class Carro {

    private Long id;
    private String fabricante;
    private String modelo;
    private Integer anoFabricacao;

    public Carro() {
    }

    public Carro(Long id, String fabricante, String modelo, Integer anoFabricacao) {
        this.id = id;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }
}