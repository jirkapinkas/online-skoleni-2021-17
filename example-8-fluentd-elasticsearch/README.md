* Projekt `demo` loguje standardnim Spring Boot zpusobem

cd demo

# Provede build image aplikace
mvnw spring-boot:build-image

cd ..

# Spusti elasticsearch, kibana, fluentd & aplikaci
docker-compose up --build -d

# Poznamka ke zpusobu logovani:

Aplikace loguje do souboru /tmp/log/myapp.log
Ten je ve volume "myapp_logs", ktery je namapovany i do fluentd kontejneru na stejne umisteni.
Diky tomu fluentd tento log "vidi" a muze z nej cist data

# Kibana:
http://localhost:5601

# Vsechno smazne:
docker-compose down
