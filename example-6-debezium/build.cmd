rem rm whole stack
rem docker stack rm myapp

docker-compose down

docker volume prune -f
docker image prune -f

rem pull all images
rem docker pull dpage/pgadmin4
rem docker pull debezium/zookeeper
rem docker pull debezium/postgres
rem docker pull debezium/postgres
rem docker pull debezium/connect

rem build
cd micro
call mvn clean package -DskipTests
rem docker run -it --rm --name my-maven-project -v %cd%:/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean package -DskipTests

cd crm
docker build --tag micro-crm .

cd ..

cd mailing
docker build --tag micro-mailing .

cd ..
cd ..

rem run docker-compose
rem docker stack deploy -c docker-compose.yml myapp
docker-compose up -d
