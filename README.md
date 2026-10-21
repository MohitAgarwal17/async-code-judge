# Async Code Judge

A Spring Boot backend for submitting code and tracking its judging status.
A client posts a submission, gets an id back immediately with status `QUEUED`,
and polls for the result.

Full design and decisions: [docs/design.md](docs/design.md)

## Tech Stack

- Java 25
- Spring Boot 4.1.1 (Web, Data JPA, Validation)
- Maven
- PostgreSQL 17 (Docker Compose)

## Prerequisites

- JDK 25
- Docker Desktop (running)
- Git

## Run Locally

1. Clone the repo:

```bash
   git clone <your-repo-url>
   cd asyn_code_judge
```

2. Start PostgreSQL:

```bash
   docker compose up -d
```

This starts a container named `code-judge-db` with a database called
`codejudge`.

3. Start the application:

```bash
   ./mvnw spring-boot:run
```

On Windows PowerShell, use `.\mvnw.cmd spring-boot:run`.

4. The API is now available at `http://localhost:8080`.

To stop everything:

```bash
docker compose down
```

Add `-v` to also delete the database data: `docker compose down -v`.

## API

| Method | Path | Description | Success |
|---|---|---|---|
| GET | `/api/submissions` | List submissions (optional `?status=QUEUED`) | 200 |
| GET | `/api/submissions/{id}` | Get one submission | 200 / 404 |
| POST | `/api/submissions` | Create a submission | 201 |
| DELETE | `/api/submissions/{id}` | Delete a submission | 204 / 404 |

### Example

Create a submission:

```bash
curl -i -X POST http://localhost:8080/api/submissions \
  -H "Content-Type: application/json" \
  -d '{"language": "java"}'
```

Response:

```json
{
  "id": 1,
  "language": "java",
  "status": "QUEUED"
}
```

The response also includes a `Location` header pointing to the new submission.

Fetch it:

```bash
curl http://localhost:8080/api/submissions/1
```

On Windows PowerShell, use `curl.exe` instead of `curl`.

## Configuration

Supported languages are set in `application.properties`:

```properties
judge.supported-languages=java,c++,python,javsscript
```


## Project Structure

```
src/main/java/com/mohit/asyn_code_judge/
├── controller/   HTTP endpoints
├── service/      business rules
├── repository/   database access (Spring Data JPA)
└── entity/       database-mapped classes
```

## Status

Week 1 complete: REST API, PostgreSQL persistence, validation and centralized
error handling. The background judging worker is planned (see the design doc).