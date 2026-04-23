
## Commands

```bash
# Build (produces target/resultado-loteria.jar)
mvn clean package

# Compile only
mvn clean compile

# Run (args: <start_draw_number> <end_draw_number>)
java -cp target/resultado-loteria.jar:$(mvn dependency:build-classpath -q -Dmdep.outputFile=/dev/stdout) br.com.prcompany.Main <start> <end>
```

No test suite is configured.

## Architecture

A single-purpose CLI utility that checks predefined Mega Sena number combinations against historical draw results from Caixa Econômica Federal's API.

**Flow:** `main(start, end)` → loop over draw numbers → HTTP GET `servicebus2.caixa.gov.br/.../megasena/{n}` → deserialize JSON → compare each combination in `LISTA_DEZENAS` against drawn numbers → print matches/prizes → stop when the next draw date is today or future.

**Key classes:**
- `Main` — entry point; hardcodes the number combinations (`LISTA_DEZENAS`) to check
- `LoteriaResponse` — Jackson DTO for a draw result (draw number, drawn numbers, prize tiers, next draw date)
- `RateioPremio` — Jackson DTO for a prize tier (description, number of winners, prize value)

**Tech:** Java 17, Maven, Jackson Databind 2.14.2, Java built-in `HttpClient`. No frameworks (no Spring, Quarkus, etc.).

**Locale note:** Dates use Brazilian format `dd/MM/yyyy`. Output is in Portuguese.
