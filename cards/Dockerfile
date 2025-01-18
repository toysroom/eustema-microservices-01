#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add the application's jar to the image
COPY target/cards-0.0.1-SNAPSHOT.jar cards.jar

# execute the application
ENTRYPOINT ["java", "-jar", "cards.jar"]