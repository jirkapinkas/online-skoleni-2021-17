# Poznámka: Je zapotřebí mít Docker + zapnutou podporu pro Kubernetes. Následně spusťte tyto příkazy:

    cd demo
    mvn spring-boot:build-image
    cd ..
    kubectl apply -f demo.yml

Abychom se podívali že byla aplikace úspěšně nasazena použijte:

    kubectl get pods

Po nasazení funguje: http://localhost:8080/actuator/health

Simulace výpadku: http://localhost:8080/simulateOutage

Tím se změní v Actuatoru status: "UP" na "DOWN", Kubernetes si toho po chvíli všimne a restartuje pod

Simulace crash aplikace: http://localhost:8080/simulateCrash

Co Kubernetes provede je vidět když se zavolá:

    kubectl get pods

    ještě lépe : watch "kubectl get pods"

