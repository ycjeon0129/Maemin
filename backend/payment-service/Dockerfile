FROM openjdk:11
COPY ./build/libs/*.jar /app/payment-service.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","payment-service.jar"]