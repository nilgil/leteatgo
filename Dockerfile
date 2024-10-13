FROM amazoncorretto:17
ENV TZ=Asia/Seoul
ENV JAVA_OPTS="-Djava.net.preferIPv4Stack=true"
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
