FROM alpine/git
WORKDIR /app
RUN git clone https://github.com/ymnliu/MedTaggerWeb.git
WORKDIR /app/MedTaggerWeb
RUN git checkout web-api
#
#FROM maven:3.5-jdk-8-alpine
#WORKDIR /app/MedTaggerWeb
#RUN mvn clean spring-boot:run

#
#FROM openjdk:8-jre-alpine
#WORKDIR /app
#COPY --from=1 /app/target/spring-petclinic-1.5.1.jar /app (4)
#CMD ["java -jar spring-petclinic-1.5.1.jar"] (5)
