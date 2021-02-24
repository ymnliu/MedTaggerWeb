screen -dmS UIMA-REST java -jar /app/MedTaggerREST/UIMA-REST-SERVER.jar -Dserver.port=8080
screen -dmS N3CNLP mvn spring-boot:run
/bin/bash
