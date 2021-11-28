FROM openjdk:11-jre-slim

EXPOSE 8080

COPY target/questionnaire-1.0-SNAPSHOT.jar ./bin/questionnaire.jar

CMD ["java", "-jar", "./bin/questionnaire.jar"]
