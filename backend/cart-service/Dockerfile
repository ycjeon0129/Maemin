FROM openjdk:11
COPY ./build/libs/*.jar /app/cart-service.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","cart-service.jar"]
