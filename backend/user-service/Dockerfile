FROM openjdk:11
COPY ./build/libs/*.jar /app/user-service.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","user-service.jar"]