# Build
mvn clean package && docker build -t rest-ref/level_zero .

# RUN

docker rm -f LevelZero || true && docker run -d -p 8080:8080 -p 4848:4848 --name LevelZero rest-ref/LevelZero 