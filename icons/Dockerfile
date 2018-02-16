FROM openjdk:8u131-jre-alpine

COPY target/icons-1.0-SNAPSHOT.jar /app/app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
