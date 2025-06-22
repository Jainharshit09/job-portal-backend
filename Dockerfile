# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-slim

WORKDIR /app
COPY --from=build /app/target/Job-Portal-0.0.1-SNAPSHOT.jar Job-Portal.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Job-Portal.jar"]
