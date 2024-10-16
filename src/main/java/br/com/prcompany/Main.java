package br.com.prcompany;

import br.com.prcompany.data.LoteriaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {

    private static final List<List<Integer>>  LISTA_DEZENAS = List.of(
            List.of(15,16,17,33,39,58),
            List.of(3,5,11,14,33,58),
            List.of(3,5,11,33,39,57));

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        final HttpClient httpClient = HttpClient.newHttpClient();

        final int initial = Integer.parseInt(args[0]);

        final int finalNumber = Integer.parseInt(args[1]);

        final ObjectMapper objectMapper = new ObjectMapper();

        final LocalDate today = LocalDate.now();

        for (int i = initial; i <= finalNumber; i++) {
            System.out.println("Chamando concurso: " + i);
            System.out.println();
            final HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI("https://servicebus2.caixa.gov.br/portaldeloterias/api/megasena/" + i)).GET().build();
            final HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() == 200) {
                final LoteriaResponse loteriaResponse = objectMapper.readValue(httpResponse.body(), LoteriaResponse.class);

                for (List<Integer> dezenas : LISTA_DEZENAS) {
                    final long count = loteriaResponse.getListaDezenas().stream().filter(l -> dezenas.contains(Integer.parseInt(l))).count();
                    System.out.println("Dezenas sorteadas: " + loteriaResponse.getListaDezenas());
                    System.out.println("Dezenas jogadas: " + dezenas);
                    System.out.println("Conta de quantos números acertados: " + count);

                    if (count > 3) {
                        System.out.println("ACERTOU _________" + count);
                    }
                    System.out.println("\n----------------------------------------------------");
                }

                System.out.printf("----------------------TERMINOU JOGO %d---------------------%n", i);

                LocalDate next = LocalDate.parse(loteriaResponse.getDataProximoConcurso(), DTF);
                if (next.isAfter(today) || next.isEqual(today)) {
                    System.out.printf("ULTIMO CONCURSO [%s]%n", loteriaResponse.getNumero());
                    System.out.printf("PROXIMO CONCURSO [%s]%n", loteriaResponse.getNumeroConcursoProximo());
                    System.out.printf("DATA PROXIMO CONCURSO [%s]%n", loteriaResponse.getDataProximoConcurso());
                    break;
                }
            } else {
                System.out.println("erro na resposta: " + httpResponse.body());
            }


        }
    }
}