FROM java:alpine AS base
WORKDIR /app

FROM base AS build
COPY . .
RUN ./gradlew test

FROM httpd:2.4 AS unit_tests
COPY --from=build /app/build/reports/tests/test/ /usr/local/apache2/htdocs/

FROM base AS app
COPY --from=build /app/build/classes/java/main/ .
ENTRYPOINT java Main
