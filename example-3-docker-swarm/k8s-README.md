# TODO:

2. Spustit build.cmd
3. Přejít na http://localhost:8080/constructEmail/1
4. Pro zastavení služeb zavolat:

		kubectl delete -f k8s-kubernetes.yml

## Rozšíření: 

Logy CRMka:

    # timto se ziska nazev podu:
    kubectl get pods
    # timto se ziskaji logy podu:
	kubectl logs POD_NAME

