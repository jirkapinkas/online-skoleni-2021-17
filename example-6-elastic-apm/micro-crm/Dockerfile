FROM adoptopenjdk/openjdk11:alpine-jre
COPY micro-crm/target/micro-crm-0.0.1-SNAPSHOT.jar /app/app.jar
COPY elastic-apm-agent-1.25.0.jar /app/elastic-apm-agent-1.25.0.jar
EXPOSE 8080
CMD ["java", "-javaagent:/app/elastic-apm-agent-1.25.0.jar", "-Delastic.apm.service_name=crm", "-Delastic.apm.server_urls=http://apm:8200", "-Delastic.apm.application_packages=com.example.microcrm", "-jar", "/app/app.jar"]
