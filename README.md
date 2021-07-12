# xChat
Sencilla aplicacion usando SpringBoot (frontend) + Vue (Backend) 

## Caracteristicas
* Rest API
* Soporte para salas de chat (chatrooms)
* Uso de Spring WebSocket (backend) y SockJS (frontend) para el intercambio de mensajes entre salas 
* Autenticacion y Autorizacion (Spring Web Security)
* Base de datos (Spring JPA)

## Servicios
* La API puede ser accedida desde localhost:8888
* El servidor de base de datos en localhost:5432 (Postgress)
* La interfaz web se accede desde localhost:8080

## Compilacion y ejecucion
1) Servidor PostgreSQL
```
cd ./postgreSQL
docker build -t pgserver .
docker run --name pgserver -e POSTGRES_PASSWORD=123456 -d pgserver
```

2) RestAPI
   
Modifica el fichero application.properties y cambia la linea por 
   la de tu servidor postgreSQL en docker (puedes usar ip addr)
   
spring.datasource.url=jdbc:postgresql://<SERVIDOR_POSTGRESQL_DOCKER>:5432/spring_chat

```
git clone https://github.com/damianS7/simple-rest-chat-backend.git
cd simple-rest-chat-backend
./mvnw package
java -jar target/*.jar
```

3) Frontend
```
https://github.com/damianS7/simple-rest-chat-frontend
```
