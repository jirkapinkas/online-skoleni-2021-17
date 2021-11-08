rem rm whole stack
docker stack rm myapp

docker volume prune -f
docker image prune -f

rem build
cd spring-boot-admin-server
call mvn clean package -DskipTests
docker build --tag spring-boot-admin-server .

cd ..

cd micro
call mvn clean package -DskipTests


cd crm
docker build --tag micro-crm .

cd ..

cd mailing
docker build --tag micro-mailing .

cd ..
cd ..

rem run docker-compose
docker stack deploy -c docker-compose.yml myapp

