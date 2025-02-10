# Usando imagem base do OpenJDK 21
FROM eclipse-temurin:21-jdk-alpine

# Configuração do diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR para a imagem
COPY target/assistances-0.0.1-SNAPSHOT.jar app.jar

# Expondo a porta 8080
EXPOSE 8080

# Comando para rodar o Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
