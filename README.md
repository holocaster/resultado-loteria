# resultado-loteria

API Quarkus para verificar combinações fixas da Mega-Sena contra concursos históricos da Caixa.

## Requisitos

- Java 21
- Maven 3.9+
- Docker (opcional, para execução em container)

## Executar localmente

```bash
mvn clean package
mvn quarkus:dev
```

Aplicação disponível em `http://localhost:8080`.

## Endpoint

`GET /megasena/verificar?inicio=<numero>&fim=<numero>`

Exemplo:

```bash
curl "http://localhost:8080/megasena/verificar?inicio=2500&fim=2510"
```

Resposta: lista de concursos com dezenas sorteadas, apostas avaliadas, acertos e dados de premiação quando aplicável.

## Container

Script de controle:

```bash
./scripts/container.sh start
./scripts/container.sh stop
```

O comando `start` faz build da imagem, remove container anterior (se existir) e sobe a aplicação em `8080`.
