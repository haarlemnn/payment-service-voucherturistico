# Build stage
FROM maven:3.8.7 AS build

WORKDIR /home/app

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean install -Pproduction

# Package stage
FROM openjdk:17.0.2-jdk

ARG JAR_FILE=/home/app/target/*.jar

WORKDIR /usr/app/

COPY --from=build ${JAR_FILE} ./app.jar
COPY docker/cert/ ./cert/

EXPOSE 8080
ENTRYPOINT ["java","-jar","./app.jar"]
