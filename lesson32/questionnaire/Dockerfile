FROM openjdk:11-jre-slim
EXPOSE 8080

COPY target/questionnaire-1.0-SNAPSHOT.jar ./bin/questionnaire.jar

CMD ["java", "-Dfile.encoding=utf-8", "$JAVA_OPTIONS", "-jar", "./bin/questionnaire.jar"]
