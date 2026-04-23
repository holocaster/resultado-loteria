package br.com.prcompany.dto;

import java.util.List;

public class ResultadoConcursoDTO {
    private int numeroConcurso;
    private String dataApuracao;
    private List<String> dezenasSorteadas;
    private boolean acumulado;
    private boolean ultimoConcurso;
    private Integer proximoConcurso;
    private String dataProximoConcurso;
    private List<ResultadoApostaDTO> apostas;

    public int getNumeroConcurso() { return numeroConcurso; }
    public void setNumeroConcurso(int numeroConcurso) { this.numeroConcurso = numeroConcurso; }

    public String getDataApuracao() { return dataApuracao; }
    public void setDataApuracao(String dataApuracao) { this.dataApuracao = dataApuracao; }

    public List<String> getDezenasSorteadas() { return dezenasSorteadas; }
    public void setDezenasSorteadas(List<String> dezenasSorteadas) { this.dezenasSorteadas = dezenasSorteadas; }

    public boolean isAcumulado() { return acumulado; }
    public void setAcumulado(boolean acumulado) { this.acumulado = acumulado; }

    public boolean isUltimoConcurso() { return ultimoConcurso; }
    public void setUltimoConcurso(boolean ultimoConcurso) { this.ultimoConcurso = ultimoConcurso; }

    public Integer getProximoConcurso() { return proximoConcurso; }
    public void setProximoConcurso(Integer proximoConcurso) { this.proximoConcurso = proximoConcurso; }

    public String getDataProximoConcurso() { return dataProximoConcurso; }
    public void setDataProximoConcurso(String dataProximoConcurso) { this.dataProximoConcurso = dataProximoConcurso; }

    public List<ResultadoApostaDTO> getApostas() { return apostas; }
    public void setApostas(List<ResultadoApostaDTO> apostas) { this.apostas = apostas; }
}
