cd demo
mvn spring-boot:build-image
cd ..
kubectl apply -f demo.yml
kubectl apply -f demo-service.yml

kubectl get svc

Funguje: http://EXTERNAL-IP:8080