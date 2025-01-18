#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y curl

# Add the application's jar to the image
COPY target/eurekaserver-0.0.1-SNAPSHOT.jar eurekaserver.jar

# execute the application
ENTRYPOINT ["java", "-jar", "eurekaserver.jar"]