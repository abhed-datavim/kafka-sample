# Spring Kafka Consumer

This project recieves Customer object from Kafka Request topic queue.

Then it fetches the list of available Drivers and picks a random Driver.

After assigning a random Driver, it sends the Driver object to Reply topic queue.

## Prerequisites
- Redis  (https://redis.io/)
- Kafka (https://kafka.apache.org)

Note
If you are looking for a quick Kafka setup on your local machine using Docker, use this command:
```docker run --rm -p 2181:2181 -p 3030:3030 -p 8081-8083:8081-8083 -p 9581-9585:9581-9585 -p 9092:9092 -e ADV_HOST=127.0.0.1 landoop/fast-data-dev:latest```

## Configuration
You can set Configuration Properties via `application.properties` which can be found in `src/main/resources`
- kafka.bootstrap-servers=localhost:9092
- kafka.topic.request-topic=request
- kafka.topic.reply-topic=reply
- kafka.group-id=datavim
- redis.uri=redis://localhost:6379
- server.port=8084

### Output
Whenever a Request queue gets a  Customer object, you can see the output in Application Console. 