```shell
# 默认 docker run -d -P --name tomcat01 --net bridge tomcat
# --link 可以单向连接
docker network create --driver bridge --subnet 172.18.0.0/16 --gateway 172.18.0.1 mynet
docker run -d -P --name tomcat01 --net mynet tomcat
docker run -d -P --name tomcat02 --net mynet tomcat
# 即可ping ip,也可以ping name
```



```yaml
version: '3.8'
services: 
	web:
		container_name: my-web
		build: .
		network:
			- mynet
		ports:
			- "5000:5000"
		volumes:
      - .:/code
      - logvolume01:/var/log
    depends_on:
    	- redis
	redis:
		image: redis:latest
		network:
			- mynet
volumes:
	logvolume01: {}
networks:
  mynets: 
```



[Get started with Docker Compose](https://docs.docker.com/compose/gettingstarted/)

[compose-file](https://docs.docker.com/compose/compose-file/compose-file-v3/)

