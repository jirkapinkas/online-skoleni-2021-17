rem build crm
cd micro-crm
call mvn clean package -DskipTests
rem docker run -it --rm --name my-maven-project -v %cd%:/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean package -DskipTests
docker build --tag micro-crm .

rem build mailing
cd ..
cd micro-mailing
call mvn clean package -DskipTests
rem docker run -it --rm --name my-maven-project -v %cd%:/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean package -DskipTests
docker build --tag micro-mailing .

cd ..

kubectl apply -f k8s-kubernetes.yml