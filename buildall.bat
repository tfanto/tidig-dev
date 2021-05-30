call docker system prune -a -f

cd tidig-dev-db
call mvn clean install -DskipTests
call createDockerImage.bat
cd ..

cd tidig-dev-server
call mvn clean install -DskipTests
call createDockerImage.bat
cd ..


docker-compose build

docker-compose up -d



