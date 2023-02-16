FROM openjdk:18
WORKDIR /app
COPY ./target/*.jar ./lancheapplication.jar
EXPOSE 8080

ENTRYPOINT java -jar lancheapplication.jar