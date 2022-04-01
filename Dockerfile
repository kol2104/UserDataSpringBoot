FROM openjdk:8-jdk-alpine
WORKDIR /usr/src/user-data
COPY ./target/user-data-0.0.1-SNAPSHOT.jar /usr/src/user-data
EXPOSE 8080
CMD ["java", "-jar", "user-data-0.0.1-SNAPSHOT.jar"]
