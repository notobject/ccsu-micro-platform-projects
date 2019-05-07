FROM java:openjdk-8-jre-alpine
COPY target/*.jar /app.jar
VOLUME /var/logs /var/logs
ENTRYPOINT ["java","-jar","/app.jar"]