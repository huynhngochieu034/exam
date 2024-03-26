FROM openjdk:22
VOLUME /tmp
EXPOSE 8080
COPY target/exam-0.0.1-SNAPSHOT.jar exam.jar
ENTRYPOINT ["java","-jar","/exam.jar"]