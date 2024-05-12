# openjdk:8 image is available in docker hub
FROM openjdk:8
WORKDIR /app

# make port 8082 and 8083 vailable outside the container
EXPOSE 8082

ADD target/*.jar /app/address-service.jar

ENTRYPOINT ["java", "-jar", "/app/address-service.jar"]