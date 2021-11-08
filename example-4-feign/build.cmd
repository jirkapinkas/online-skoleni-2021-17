rem rm whole stack
docker stack rm myapp

docker volume prune -f
docker image prune -f

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
docker stack deploy -c docker-compose.yml myapp

