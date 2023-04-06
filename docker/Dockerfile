FROM openjdk:17
RUN ["mkdir", "app"]
WORKDIR app
COPY *.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]