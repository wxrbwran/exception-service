### portainer
```shell
docker run -d -p 9000:9000 --name portainer  -v /var/run/docker.sock:/var/run/docker.sock -v \
    /root/docker/portainer:/data portainer/portainer
```



### postgres
```shell
docker run --name postgres -e POSTGRES_PASSWORD=123456 -p 5432:5432 \
-v /root/docker/pg:/var/lib/postgresql/data -d postgres:latest
```



### zookeeper
```shell
docker run -d --restart=always --log-driver json-file --log-opt max-size=100m --log-opt max-file=2 \
    --name zookeeper -p 2181:2181 -v /etc/localtime:/etc/localtime wurstmeister/zookeeper
```



### filebeat 
```shell
docker run -d --name filebeat \
    -v /root/docker/filebeat/filebeat.yml:/usr/share/filebeat/filebeat.yml \
    -v /root/docker/filebeat/data:/usr/share/filebeat/data \
    -v /var/logs:/logs \
    elastic/filebeat:7.10.1
```



### kafka
```shell
docker run -d --restart=always --log-driver json-file --log-opt max-size=100m --log-opt max-file=2 \
    --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_HEAP_OPTS="-Xmx512M -Xms512M" \
    -e KAFKA_ZOOKEEPER_CONNECT=172.17.0.3:2181/kafka \
    -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://103.105.200.216:9092 \
    -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -v /etc/localtime:/etc/localtime wurstmeister/kafka
```



### nacos
```shell
docker run --name nacos -v /root/docker/nacos/nacos.properties:/home/nacos/conf/application.properties \
    -e MODE=standalone -p 8848:8848 -d nacos/nacos-server:2.0.3
```



### seata-server
```shell
docker run -d  --name seata-server -p 8091:8091 seataio/seata-server:1.4.2
```



### sentinel
```shell
docker run --name sentinel  -d -p 8858:8858 -d  bladex/sentinel-dashboard:1.8.0
```

