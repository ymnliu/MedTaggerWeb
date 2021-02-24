cd /app/MedTaggerREST/
screen -dmS UIMA-REST java -jar UIMA-REST-SERVER.jar -Dserver.port=8080
cd /app/N3CMedTagger
mvn spring-boot:run
