FROM eclipse-temurin:21-jdk-alpine

# Criando um usuário não-root para rodar a aplicação
RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

# Ajustar permissões para o usuário não-root acessar o arquivo
RUN chown spring:spring app.jar

EXPOSE 8080

USER spring

ENTRYPOINT ["java", "-jar", "app.jar"]
