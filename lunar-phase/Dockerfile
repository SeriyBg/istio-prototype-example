FROM openjdk:8u131-jre-alpine

COPY target/lunar-phase-1.0-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
