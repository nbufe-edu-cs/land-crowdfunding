docker stop land-crowdfunding
docker rm land-crowdfunding
docker rmi land-crowdfunding
docker build -t land-crowdfunding .
docker run --name land-crowdfunding -p 8081:8081 --link mysql:mysql -d land-crowdfunding:latest