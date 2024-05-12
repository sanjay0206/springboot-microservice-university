# openjdk:8 image is available in docker hub
FROM openjdk:8
WORKDIR /app

# make port 8082 and 8083 vailable outside the container
EXPOSE 8761

ADD target/eureka-server.jar /app/eureka-server.jar

ENTRYPOINT ["java", "-jar", "/app/eureka-server.jar"]