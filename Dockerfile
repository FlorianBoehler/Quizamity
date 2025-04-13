# Basic-Image with JDK 21
FROM eclipse-temurin:21-jdk

# Payara Micro herunterladen
RUN curl -L -o payara-micro.jar https://repo1.maven.org/maven2/fish/payara/distributions/payara-micro/6.2024.2/payara-micro-6.2024.2.jar

# Work directory
WORKDIR /app

# Copy WAR File into Image (after Build)
COPY target/quizamity-1.0-SNAPSHOT.war .

# Start Payara Micro with WAR-Deployment
CMD ["java", "-jar", "/payara-micro.jar", "--deploy", "/app/quizamity-1.0-SNAPSHOT.war", "--port", "8080"]
