FROM openjdk:11
COPY ./build/libs/*.jar /app/pay-service.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","pay-service.jar"]