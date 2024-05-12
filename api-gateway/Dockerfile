


# openjdk:8 image is available in docker hub
FROM openjdk:8
WORKDIR /app

# make port 8082 and 8083 vailable outside the container
EXPOSE 9090

ADD target/api-gateway.jar /app/api-gateway.jar

ENTRYPOINT ["java", "-jar", "/app/api-gateway.jar"]