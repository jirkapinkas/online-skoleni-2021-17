# TODO:

1. Jednorázově zavolat: docker swarm init
2. Spustit build.cmd
3. Přejít na http://localhost:8080/constructEmail/1
4. Pro zastavení služeb zavolat:

		docker stack rm myapp

## Rozšíření: 

Logy CRMka:

    # timto se ziska nazev service
    docker service ls
    # timto se ziskaji logy service (muze byt i vic replik)
	docker service logs -f myapp_crm

