FROM openjdk:11
COPY ./build/libs/*.jar /app/discovery-service.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","discovery-service.jar"]