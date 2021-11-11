FROM adoptopenjdk/openjdk11:alpine-jre
COPY micro-products/target/micro-products-0.0.1-SNAPSHOT.jar /app/app.jar
COPY elastic-apm-agent-1.25.0.jar /app/elastic-apm-agent-1.25.0.jar
EXPOSE 8080
CMD ["java", "-javaagent:/app/elastic-apm-agent-1.25.0.jar", "-Delastic.apm.service_name=products", "-Delastic.apm.server_urls=http://apm:8200", "-Delastic.apm.application_packages=com.example.microproducts", "-jar", "/app/app.jar"]
