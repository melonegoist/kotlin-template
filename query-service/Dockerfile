FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml ./
COPY common/pom.xml common/pom.xml
COPY ingestion-service/pom.xml ingestion-service/pom.xml
COPY query-service/pom.xml query-service/pom.xml
COPY common/src common/src
COPY query-service/src query-service/src
RUN mvn -pl query-service -am -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/query-service/target/query-service-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
