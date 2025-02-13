FROM eclipse-temurin:21-jdk-alpine

RUN apk update && apk add maven

WORKDIR /app

COPY . /app

RUN mvn clean package

COPY target/assistance-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]
