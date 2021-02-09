FROM maven:3.5-jdk-8-alpine
COPY . /app 
WORKDIR /app

RUN mvn clean install -DskipTests

EXPOSE 80:8080
CMD ["mvn", "spring-boot:run"]

