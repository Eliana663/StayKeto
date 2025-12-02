# Usar Java 17 Alpine
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Build del jar con Maven (si usas Gradle, cambia el comando)
RUN ./mvnw clean package -DskipTests

# Segunda etapa: runtime
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar el jar desde la etapa de build
COPY --from=build /app/target/stayketo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar","--server.port=$PORT"]

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
