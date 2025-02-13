# Usando imagem base do OpenJDK 21
FROM eclipse-temurin:21-jdk-alpine

# Instalando dependências (se necessário)
RUN apk update && apk add maven

# Configuração do diretório de trabalho
WORKDIR /app

# Copiar o repositório para dentro do contêiner
COPY . /app

# Rodar o comando para construir o .jar (assumindo que você está usando Maven)
RUN mvn clean package

# Copiar o arquivo .jar gerado para dentro da imagem Docker
COPY target/assistances-0.0.1-SNAPSHOT.jar app.jar

# Expondo a porta 8080
EXPOSE 8080

# Comando para rodar o Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
