FROM maven:3.5-jdk-8-alpine
RUN mkdir -p /app/N3CMedTagger
RUN cp -r . /app/N3CMedTagger
RUN mkdir -p /app/work

WORKDIR /app/work

RUN git clone https://github.com/OHNLP/UIMA-Stream-Server
WORKDIR /app/work/UIMA-Stream-Server
RUN mvn clean install -DskipTests -P EXECUTABLE
RUN mkdir -p /app/MedTaggerREST
RUN cp UIMA-Server-REST/target/UIMA-REST-SERVER.jar /app/MedTaggerREST/UIMA-REST-SERVER.jar
RUN mkdir -p /app/MedTaggerREST/libs
RUN mkdir -p /app/MedTaggerREST/plugins
WORKDIR /app/work/
RUN wget -O MedTagger.zip https://github.com/OHNLP/MedTagger/releases/download/v1.0.9/MedTagger.zip
RUN unzip MedTagger.zip
RUN cp /app/work/MedTagger/MedTagger.jar /app/MedTaggerREST/libs
RUN git clone https://github.com/OHNLP/MedTaggerRESTPlugin.git
WORKDIR /app/work/MedTaggerRESTPlugin
RUN mvn clean install -DskipTests
RUN cp target/MedTaggerRESTPlugin.jar /app/MedTaggerREST/plugins/MedTaggerRESTPlugin.jar
RUN cp /app/N3CMedTagger/src/main/resources/uima-stream-server-conf.json /app/MedTaggerREST/medtagger_rest_config.json

EXPOSE 8080:8080

WORKDIR /app/N3CMedTagger

RUN -d java -jar /app/MedTaggerREST/UIMA-REST-SERVER.jar -Dserver.port=8080
RUN rm -r /app/work

RUN mvn clean install -DskipTests

EXPOSE 80:80
CMD ["mvn", "spring-boot:run"]

