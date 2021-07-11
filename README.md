# SimpleRestChat
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
>Backend
```
git clone https://github.com/damianS7/simple-rest-chat-backend.git
cd simple-rest-chat-backend
./mvnw package
java -jar target/*.jar
```
> Servidor postgreSQL
```
docker pull ...
```
> Frontend (https://github.com/damianS7/simple-rest-chat-frontend)
