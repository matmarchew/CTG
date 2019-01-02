FROM java:alpine
WORKDIR /app
COPY . .
RUN ./gradlew build
ENTRYPOINT ./gradlew bootRun
