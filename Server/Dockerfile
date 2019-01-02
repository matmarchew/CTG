FROM java:alpine AS base
WORKDIR /app

FROM base AS app
COPY . .
RUN ./gradlew test
ENTRYPOINT ./gradlew run

FROM httpd:2.4 AS unit_tests
COPY --from=app /app/build/reports/tests/test/ /usr/local/apache2/htdocs/
