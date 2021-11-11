# stop docker-compose
docker-compose down

# build crm
cd micro-crm
mvn clean package -DskipTests

cd ..

# build products
cd micro-products
mvn clean package -DskipTests

cd ..

# build mailing
cd micro-mailing
mvn clean package -DskipTests

cd ..

# run docker-compose
docker-compose up --build -d

