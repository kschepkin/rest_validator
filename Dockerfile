FROM openjdk:8
RUN  curl -L "https://github.com/kschepkin/rest_validator/releases/download/0.1.3/RestValidator-0.1.3.jar" -o /app.jar && mkdir /schemes
ENTRYPOINT ["java","-jar","/app.jar"]