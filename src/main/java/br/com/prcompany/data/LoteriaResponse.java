package br.com.prcompany.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoteriaResponse {

    private boolean acumulado;

    private String dataApuracao;

    private String dataProximoConcurso;

    private List<String> listaDezenas;

    private List<RateioPremio> listaRateioPremio;

    private Integer numero;

    private Integer numeroConcursoProximo;

    public Integer getNumeroConcursoProximo() {
        return numeroConcursoProximo;
    }

    public void setNumeroConcursoProximo(Integer numeroConcursoProximo) {
        this.numeroConcursoProximo = numeroConcursoProximo;
    }

    public boolean isAcumulado() {
        return acumulado;
    }

    public void setAcumulado(boolean acumulado) {
        this.acumulado = acumulado;
    }

    public String getDataApuracao() {
        return dataApuracao;
    }

    public void setDataApuracao(String dataApuracao) {
        this.dataApuracao = dataApuracao;
    }

    public String getDataProximoConcurso() {
        return dataProximoConcurso;
    }

    public void setDataProximoConcurso(String dataProximoConcurso) {
        this.dataProximoConcurso = dataProximoConcurso;
    }

    public List<String> getListaDezenas() {
        return listaDezenas;
    }

    public void setListaDezenas(List<String> listaDezenas) {
        this.listaDezenas = listaDezenas;
    }

    public List<RateioPremio> getListaRateioPremio() {
        return listaRateioPremio;
    }

    public void setListaRateioPremio(List<RateioPremio> listaRateioPremio) {
        this.listaRateioPremio = listaRateioPremio;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "LoteriaResponse{" +
                "acumulado=" + acumulado +
                ", dataApuracao='" + dataApuracao + '\'' +
                ", dataProximoConcurso='" + dataProximoConcurso + '\'' +
                ", listaDezenas=" + listaDezenas +
                ", listaRateioPremio=" + listaRateioPremio +
                ", numero=" + numero +
                '}';
    }
}
