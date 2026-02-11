# Commerce Express

An online shopping REST API built with Spring Boot.

<!-- QUALITY-BADGES-START -->
![Quality Grade](https://img.shields.io/badge/quality-pending-yellow)
![Bugs](https://img.shields.io/badge/bugs-pending-yellow)
![Vulnerabilities](https://img.shields.io/badge/vulnerabilities-pending-yellow)
![Code Smells](https://img.shields.io/badge/code%20smells-pending-yellow)
![Mutation Score](https://img.shields.io/badge/mutation%20score-pending-yellow)
<!-- QUALITY-BADGES-END -->

## Tech Stack

- **Backend**: Java 21, Spring Boot 3.5
- **Database**: H2 (development), PostgreSQL (production)
- **API Docs**: SpringDoc OpenAPI
- **Testing**: JUnit 5, Spring Test, Testcontainers

## Getting Started

```bash
cd server
./mvnw spring-boot:run
```

## API Documentation

Once running, visit: http://localhost:8080/swagger-ui.html

## Quality Metrics

This project uses automated quality analysis:

| Tool | Purpose |
|------|---------|
| SpotBugs | Bug detection |
| PMD | Code quality/smells |
| OWASP Dependency Check | Security vulnerabilities |
| JaCoCo | Code coverage |
| PIT | Mutation testing |

Badges are automatically updated on every push to master.
