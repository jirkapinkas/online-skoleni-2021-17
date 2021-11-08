# TODO:

1. Spustit build.cmd

Aplikace běží na:
	http://localhost:8080

Metriky aplikace:
	http://localhost:8080/actuator/prometheus

Prometheus běží na:
	http://localhost:9090

Grafana běží na:
	http://localhost:3000

2. Přihlásit se do Grafana (admin / admin)

3. Propojit Prometheus s Grafana (nastavit tzv. DataSource),
   URL Prometheus serveru: http://prometheus:9090

4. Vypnutí:

	docker-compose down