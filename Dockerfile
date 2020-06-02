FROM maven:3.5-jdk-8-alpine
COPY . /app 
WORKDIR /app

RUN mvn clean install 

EXPOSE 80
CMD ["mvn", "spring-boot:run"]

