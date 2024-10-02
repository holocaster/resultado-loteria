package br.com.prcompany.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RateioPremio {

    private String descricaoFaixa;

    private Integer faixa;

    private Integer numeroDeGanhadores;

    private BigDecimal valorPremio;

    public String getDescricaoFaixa() {
        return descricaoFaixa;
    }

    public void setDescricaoFaixa(String descricaoFaixa) {
        this.descricaoFaixa = descricaoFaixa;
    }

    public Integer getFaixa() {
        return faixa;
    }

    public void setFaixa(Integer faixa) {
        this.faixa = faixa;
    }

    public Integer getNumeroDeGanhadores() {
        return numeroDeGanhadores;
    }

    public void setNumeroDeGanhadores(Integer numeroDeGanhadores) {
        this.numeroDeGanhadores = numeroDeGanhadores;
    }

    public BigDecimal getValorPremio() {
        return valorPremio;
    }

    public void setValorPremio(BigDecimal valorPremio) {
        this.valorPremio = valorPremio;
    }

    @Override
    public String toString() {
        return "RateioPremio{" +
                "descricaoFaixa='" + descricaoFaixa + '\'' +
                ", faixa=" + faixa +
                ", numeroDeGanhadores=" + numeroDeGanhadores +
                ", valorPremio=" + valorPremio +
                '}';
    }
}
