# TODO:

1. Spustit build.cmd
2. Přejít na http://localhost:8080/constructEmail/1
3. Pro zobrazení logů zavolat:

		docker-compose logs --follow
		
4. Pro zastavení služeb zavolat:

		docker-compose down

## Rozšíření: 

Hazelcast management center: http://localhost:8090

Je zapotřebí Cluster Config a nastavit v něm Member Addresses: hc1, hc2, hc3