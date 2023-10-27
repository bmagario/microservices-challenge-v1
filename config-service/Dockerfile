FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./target/config-service-1.0-SNAPSHOT.jar ./config-service.jar
EXPOSE 8081
CMD ["java", "-jar", "config-service.jar"]