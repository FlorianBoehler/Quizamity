# === BUILD STAGE ===
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# === RUNTIME STAGE ===
FROM eclipse-temurin:21-jdk

# Download Payara Micro
RUN curl -L -o /payara-micro.jar https://repo1.maven.org/maven2/fish/payara/extras/payara-micro/6.2024.2/payara-micro-6.2024.2.jar

WORKDIR /app

# Copy WAR from build stage
COPY --from=build /app/target/quizamity-1.0-SNAPSHOT.war .
COPY postboot.asadmin .

# Expose HTTP port
EXPOSE 8080

# Start Payara Micro with JNDI data source config
CMD ["java", "-jar", "/payara-micro.jar", "--deploy", "/quizamity-1.0-SNAPSHOT.war", "--postbootcommandfile", "/postboot.asadmin"]