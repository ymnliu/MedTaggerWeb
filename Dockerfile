FROM maven:3.5-jdk-8-alpine
RUN mkdir -p /app/N3CMedTagger \
    cp -r . /app/N3CMedTagger \
    mkdir -p /app/work

WORKDIR /app/work

RUN git clone https://github.com/OHNLP/UIMA-Stream-Server \
    cd /app/work/UIMA-Stream-Server \
    mvn clean install -DskipTests -P EXECUTABLE \
    mkdir -p /app/MedTaggerREST \
    cp UIMA-Server-REST/target/UIMA-REST-SERVER.jar /app/MedTaggerREST/UIMA-REST-SERVER.jar \
    mkdir -p /app/MedTaggerREST/libs \
    mkdir -p /app/MedTaggerREST/plugins\
    cd /app/work/ \
    wget -O MedTagger.zip https://github.com/OHNLP/MedTagger/releases/download/v1.0.9/MedTagger.zip \
    unzip MedTagger.zip \
    cp /app/work/MedTagger/MedTagger.jar /app/MedTaggerREST/libs \
    git clone https://github.com/OHNLP/MedTaggerRESTPlugin.git \
    cd MedTaggerRESTPlugin \
    mvn clean install -DskipTests \
    cp target/MedTaggerRESTPlugin.jar /app/MedTaggerREST/plugins/MedTaggerRESTPlugin.jar \
    cp /app/N3CMedTagger/src/main/resources/uima-stream-server-conf.json /app/MedTaggerREST/medtagger_rest_config.json

EXPOSE 8080:8080

RUN -d java -jar /app/MedTaggerREST/UIMA-REST-SERVER.jar -Dserver.port=8080
RUN rm -r /app/work

WORKDIR /app/N3CMedTagger

RUN mvn clean install -DskipTests

EXPOSE 80:80
CMD ["mvn", "spring-boot:run"]

