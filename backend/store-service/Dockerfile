FROM openjdk:11
COPY ./build/libs/*.jar /app/store-service.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","store-service.jar"]
