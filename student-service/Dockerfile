# openjdk:8 image is available in docker hub
FROM openjdk:8
WORKDIR /app

# make port 8080 vailable outside the container
EXPOSE 8080

ADD target/*.jar /app/student-service.jar

ENTRYPOINT ["java", "-jar", "/app/student-service.jar"]