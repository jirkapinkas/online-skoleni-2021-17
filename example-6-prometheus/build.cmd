rem rm whole stack
docker-compose down

docker volume prune -f
docker image prune -f

rem build
cd monitoring
call mvn clean package -DskipTests
docker build --tag monitoring-app .

cd ..

rem run docker-compose
docker-compose up -d

