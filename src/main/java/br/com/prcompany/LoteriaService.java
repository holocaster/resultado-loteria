package br.com.prcompany;

import br.com.prcompany.data.LoteriaResponse;
import br.com.prcompany.data.RateioPremio;
import br.com.prcompany.dto.ResultadoApostaDTO;
import br.com.prcompany.dto.ResultadoConcursoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LoteriaService {

    private static final List<List<Integer>> LISTA_DEZENAS = List.of(
            List.of(15, 16, 17, 33, 39, 58),
            List.of(3, 5, 11, 14, 33, 58),
            List.of(3, 5, 11, 33, 39, 57));

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Inject
    ObjectMapper objectMapper;

    public List<ResultadoConcursoDTO> verificarConcursos(int inicio, int fim) {
        List<ResultadoConcursoDTO> resultados = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (int i = inicio; i <= fim; i++) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("https://servicebus2.caixa.gov.br/portaldeloterias/api/megasena/" + i))
                        .GET()
                        .build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    continue;
                }

                LoteriaResponse loteria = objectMapper.readValue(response.body(), LoteriaResponse.class);

                List<ResultadoApostaDTO> apostas = new ArrayList<>();
                for (List<Integer> dezenas : LISTA_DEZENAS) {
                    long count = loteria.getListaDezenas().stream()
                            .filter(l -> dezenas.contains(Integer.parseInt(l)))
                            .count();

                    ResultadoApostaDTO aposta = new ResultadoApostaDTO();
                    aposta.setDezenasJogadas(dezenas);
                    aposta.setAcertos(count);
                    aposta.setPremiado(count > 3);

                    if (count > 3) {
                        RateioPremio rateioPremio = loteria.getListaRateioPremio().stream()
                                .filter(r -> r.getDescricaoFaixa().contains(String.valueOf(count)))
                                .findFirst()
                                .orElse(null);
                        if (rateioPremio != null) {
                            aposta.setValorPremio(rateioPremio.getValorPremio());
                            aposta.setDescricaoPremio(rateioPremio.getDescricaoFaixa());
                        }
                    }
                    apostas.add(aposta);
                }

                LocalDate nextDate = LocalDate.parse(loteria.getDataProximoConcurso(), DTF);
                boolean ultimo = nextDate.isAfter(today) || nextDate.isEqual(today);

                ResultadoConcursoDTO resultado = getResultadoConcursoDTO(loteria, ultimo, apostas);
                resultados.add(resultado);

                if (ultimo) {
                    break;
                }

            } catch (Exception e) {
                throw new RuntimeException("Erro ao consultar concurso " + i + ": " + e.getMessage(), e);
            }
        }

        return resultados;
    }

    @Nonnull
    private static ResultadoConcursoDTO getResultadoConcursoDTO(LoteriaResponse loteria, boolean ultimo, List<ResultadoApostaDTO> apostas) {
        ResultadoConcursoDTO resultado = new ResultadoConcursoDTO();
        resultado.setNumeroConcurso(loteria.getNumero());
        resultado.setDataApuracao(loteria.getDataApuracao());
        resultado.setDezenasSorteadas(loteria.getListaDezenas());
        resultado.setAcumulado(loteria.isAcumulado());
        resultado.setUltimoConcurso(ultimo);
        resultado.setProximoConcurso(loteria.getNumeroConcursoProximo());
        resultado.setDataProximoConcurso(loteria.getDataProximoConcurso());
        resultado.setApostas(apostas);
        return resultado;
    }
}
