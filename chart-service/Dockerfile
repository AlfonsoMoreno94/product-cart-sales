FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/chart-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app_chart_service.jar

ENTRYPOINT ["java", "-jar", "app_chart_service.jar"]