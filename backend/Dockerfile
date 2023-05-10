FROM openjdk
WORKDIR /app

COPY ./target/projectfiler-docker.jar /app/projectfiler-docker.jar

EXPOSE 3000

CMD ["java", "-jar", "projectfiler-docker.jar"]