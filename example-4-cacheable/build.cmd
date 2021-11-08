rem stop docker-compose
docker-compose down

rem build crm
cd micro-crm
call mvn clean package -DskipTests

rem build mailing
cd ..
cd micro-mailing
call mvn clean package -DskipTests

cd ..

rem run docker-compose
docker-compose up --build -d

