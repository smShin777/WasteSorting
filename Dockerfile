FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY demo/src ./src
COPY demo/build.gradle .
COPY demo/settings.gradle .
COPY demo/gradlew .
COPY demo/gradle ./gardle
RUN chmod +x gralew
RUN ./gradle bootJar --no-dameon -x test

FROM openjdk:17-jre-slim
WORKDIR /app
COPY --from=build /app/bulid/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
