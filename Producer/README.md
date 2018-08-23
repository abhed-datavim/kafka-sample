# Spring Kafka Producer

This project produces Customer objects to the Request topic queue of Kafka.

It also fetches assigned Driver objects from the Reply topic queue.

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
- server.port=8082

## Add Drivers 
To add drivers in Redis, you can use this API
- GET localhost:{port}/adddriver 


## Assign Drivers
POST a customer JSON object with the following schema, in order to get the Assigned Driver object

```
{
	"name":"John",
	"id":"1"
}
```
- POST localhost:{port}/assigndriver

### Output
After calling the `/assigndriver` API, you can see the result in Application Console. 