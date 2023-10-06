FROM openjdk:11
COPY ./build/libs/*.jar /app/card.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","card.jar"]