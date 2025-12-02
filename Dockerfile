# Etapa de build
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Dar permisos de ejecución al mvnw
RUN chmod +x mvnw

# Build del jar sin tests
RUN ./mvnw clean package -DskipTests

# Etapa de runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiar el jar desde la etapa de build
COPY --from=build /app/target/stayketo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar","--server.port=$PORT"]

