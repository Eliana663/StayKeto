# -------------------------------
# Etapa de build
# -------------------------------
FROM eclipse-temurin:17-jdk-alpine AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar todo el proyecto al contenedor
COPY . .

# Dar permisos de ejecución al mvnw (importante para Linux)
RUN chmod +x mvnw

# Construir el JAR y saltarse los tests
RUN ./mvnw clean package -DskipTests

# -------------------------------
# Etapa de runtime
# -------------------------------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar el JAR generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando para arrancar la app, usando la variable de Render $PORT
ENTRYPOINT ["java","-jar","/app/app.jar","--server.port=$PORT"]


