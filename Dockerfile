FROM maven:3.8.3-openjdk-17
WORKDIR /app
COPY . .
RUN mvn package
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]