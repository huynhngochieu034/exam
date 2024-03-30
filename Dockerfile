FROM maven:3-eclipse-temurin-21-alpine AS build
WORKDIR /home/app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:21
COPY --from=build /home/app/target/exam-0.0.1-SNAPSHOT.jar /usr/local/lib/exam.jar
CMD ["java", "-jar", "/usr/local/lib/exam.jar"]