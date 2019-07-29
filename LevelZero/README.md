# Build
mvn clean package && docker build -t rest-ref/level_zero .

# RUN

docker rm -f level_zero || true && docker run -d -p 8080:8080 -p 4848:4848 --name level_zero rest-ref/level_zero 
