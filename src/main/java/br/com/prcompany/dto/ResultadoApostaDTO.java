package br.com.prcompany.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultadoApostaDTO {
    private List<Integer> dezenasJogadas;
    private long acertos;
    private boolean premiado;
    private BigDecimal valorPremio;
    private String descricaoPremio;

    public List<Integer> getDezenasJogadas() { return dezenasJogadas; }
    public void setDezenasJogadas(List<Integer> dezenasJogadas) { this.dezenasJogadas = dezenasJogadas; }

    public long getAcertos() { return acertos; }
    public void setAcertos(long acertos) { this.acertos = acertos; }

    public boolean isPremiado() { return premiado; }
    public void setPremiado(boolean premiado) { this.premiado = premiado; }

    public BigDecimal getValorPremio() { return valorPremio; }
    public void setValorPremio(BigDecimal valorPremio) { this.valorPremio = valorPremio; }

    public String getDescricaoPremio() { return descricaoPremio; }
    public void setDescricaoPremio(String descricaoPremio) { this.descricaoPremio = descricaoPremio; }
}
