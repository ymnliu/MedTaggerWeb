FROM maven:3.5-jdk-8-alpine
RUN apk add --no-cache git
RUN apk add --no-cache screen
RUN mkdir -p /app/N3CMedTagger
COPY . /app/N3CMedTagger
RUN mkdir -p /app/work

WORKDIR /app/work

RUN git clone https://github.com/OHNLP/UIMA-Stream-Server
WORKDIR /app/work/UIMA-Stream-Server
RUN mvn clean install -DskipTests -P EXECUTABLE
RUN mkdir -p /app/MedTaggerREST
RUN cp /app/work/UIMA-Stream-Server/UIMA-Server-REST/target/UIMA-REST-SERVER.jar /app/MedTaggerREST/UIMA-REST-SERVER.jar
RUN mkdir -p /app/MedTaggerREST/libs
RUN mkdir -p /app/MedTaggerREST/plugins
RUN mkdir -p /app/work/MedTagger
WORKDIR /app/work/MedTagger
RUN wget -O MedTagger.zip https://github.com/OHNLP/MedTagger/releases/download/v1.0.9/MedTagger.zip
RUN unzip MedTagger.zip
RUN cp MedTagger.jar /app/MedTaggerREST/libs
RUN mkdir -p /app/work/MedTaggerRESTPlugin
WORKDIR /app/work/MedTaggerRESTPlugin
RUN wget -O MedTaggerRESTPlugin.zip https://github.com/OHNLP/MedTaggerRESTPlugin/releases/download/v1.0.1/MedTaggerRESTPlugin.zip
RUN unzip MedTaggerRESTPlugin.zip
RUN cp /app/work/MedTaggerRESTPlugin/plugins/MedTaggerRESTPlugin.jar /app/MedTaggerREST/plugins/MedTaggerRESTPlugin.jar
RUN cp /app/N3CMedTagger/src/main/resources/uima-stream-server-conf.json /app/MedTaggerREST/medtagger_rest_config.json

EXPOSE 8080:8080

WORKDIR /app/N3CMedTagger

RUN rm -r /app/work

RUN mvn clean install -DskipTests

EXPOSE 80:80
CMD ["./docker_run.sh"]

