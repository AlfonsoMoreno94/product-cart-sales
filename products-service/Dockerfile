FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/products-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app_products_service.jar

ENTRYPOINT ["java", "-jar", "app_products_service.jar"]
