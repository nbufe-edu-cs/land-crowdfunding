docker stop land-crowdfunding
docker rm land-crowdfunding
docker rmi land-crowdfunding
docker build -t land-crowdfunding .
docker run -d -p 8081:8081 --name land-crowdfunding land-crowdfunding:latest