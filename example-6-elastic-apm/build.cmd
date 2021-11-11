rem stop docker-compose
docker-compose down

rem build crm
cd micro-crm
call mvn clean package -DskipTests

cd ..

rem build products
cd micro-products
call mvn clean package -DskipTests

cd ..

rem build mailing
cd micro-mailing
call mvn clean package -DskipTests

cd ..

rem run docker-compose
docker-compose up --build -d

