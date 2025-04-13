# === BUILD STAGE ===
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy Maven project
COPY pom.xml .
COPY src ./src

# Build WAR without tests
RUN mvn clean package -DskipTests

# === RUNTIME STAGE ===
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Download Payara Micro
RUN curl -L -o /payara-micro.jar https://repo1.maven.org/maven2/fish/payara/extras/payara-micro/6.2024.2/payara-micro-6.2024.2.jar

# Download PostgreSQL JDBC driver
RUN curl -L -o /postgresql.jar https://jdbc.postgresql.org/download/postgresql-42.7.3.jar

# Copy built WAR
COPY --from=build /app/target/quizamity-1.0-SNAPSHOT.war ./quizamity.war

# Copy startup scripts
COPY create-datasource.asadmin ./create-datasource.asadmin
COPY entrypoint.sh ./entrypoint.sh

# Make entrypoint executable
RUN chmod +x ./entrypoint.sh

# Use custom entrypoint script
ENTRYPOINT ["/app/entrypoint.sh"]
