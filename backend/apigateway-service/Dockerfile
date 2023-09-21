FROM openjdk:11
COPY ./build/libs/*.jar /app/apigateway-service.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","apigateway-service.jar"]