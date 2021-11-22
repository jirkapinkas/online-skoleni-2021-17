# TODO:

Spustit build.cmd

Poté je zapotřebí zavolat:

docker exec -it KAFKA_CONTAINER_NAME bash

Tím se dostaneme dovnitř Kafka kontejneru, kde zavoláme:

curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" connect:8083/connectors/ -d '{
"name": "customer-outbox-connector", 
"config": {
  "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
  "tasks.max": "1",
  "database.hostname": "postgres_1",
  "database.port": "5432",
  "database.user": "postgres",
  "database.password": "postgres",
  "database.dbname" : "postgres",
  "database.server.name": "localhost",
  "tombstones.on.delete" : "false",
  "table.whitelist" : "public.outbox_event",
  "transforms" : "outbox",
  "transforms.outbox.type" : "io.debezium.transforms.outbox.EventRouter",
  "transforms.outbox.table.fields.additional.placement" : "type:envelope:eventType"
  }
}'


A tím se vytvoří Debezium connector. 

Poté například v Postmanu můžeme zavolat POST požadavek na http://localhost:9090/customers
s obsahem:

	{ "name" : "jirka", "email" : "jirka@email.com" }

A tím se provede uložení tohoto záznamu do databáze CRMka a Debezium to přes Kafku zreplikuje do databáze MAILINGu. Jak se dostat do databáze CRM / MAILING?

Přejděte na: http://localhost:5050/ (zde se nachází pgAdmin)

Zadejte:

username: admin@admin.cz
password: admin


Přidejte 2 servery:

název serveru: postgres_1 (toto je CRMko)
username: postgres
password: postgres

název serveru: postgres_2 (toto je MAILING)
username: postgres
password: postgres


Vypnutí:

	docker-compose down

## Rozšíření: 

