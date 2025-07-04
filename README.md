# Camunda Challenge : The animal picture app
This project is a Spring Boot application that allows uploading and viewing animal images (cat, dog, or bear) with Docker and Maven build support.

<Br/> 

## Prerequisites
Ensure the following software packages are installed on your system. The java application has been built with the Jdk17 and Maven 3.9.10 :
- [JDK 17](https://adoptium.net/en-GB/)
- [Maven 3.9.10](https://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/get-docker/)

<Br/>

## Setup Instructions
1. **Clone the existing repository:**
```bash
   git clone https://github.com/your-username/camunda.git
   cd camunda
```

2. **Position into the folder:**
```bash
   cd camunda
```

3. **Build and package the application:**
```bash
   mvn clean package
```
4. **Containize and run the application:**
```bash
   docker-compose up --build
```
<br/>

## How to test the application (REST Endpoints)
Use a REST client like **Curl**, **Postman** or **Insomnia** to test the following HTTP endpoints.
Here are the 2 exposed methods in the application.


**1. POST `/api/animals` : Fetches animal images and stores them in an in-memory H2 database.**

**2. GET `/api/animals` : Fetches the latest item (animal image) uploaded in the database.**



## Examples of requests

HTTP POST : http://localhost:8080/api/animals
```bash
curl -X POST http://localhost:8080/api/animals \
  -H "Content-Type: application/json" \
  -d '{"type": "dog", "count": 3}'
```

HTTP GET : http://localhost:8080/api/animals
```bash
curl -X GET http://localhost:8080/api/animals \
  -H "Content-Type: application/json" 
```

<br/>

## Examples of requests (Error Handling) 
Error handling is enabled on type (except cat, dog, bear) and count (not null, min:1 and max:10)

```bash
curl -X POST http://localhost:8080/api/animals \
  -H "Content-Type: application/json" \
  -d '{"type": "fish", "count": 3}'
```
or
```bash
curl -X POST http://localhost:8080/api/animals \
  -H "Content-Type: application/json" \
  -d '{"type": "xxxx", "count": 100}'
```
or
```bash
curl -X POST http://localhost:8080/api/animals \
  -H "Content-Type: application/json" \
  -d '{"type": "", "count": 0}'
```

<br/>

![screenshot-http-post](https://github.com/user-attachments/assets/60446a71-5f57-419e-b138-5c2af60d89fc)


<br/><br/>
## How to test the application (UI)

Open your browser and enter the following URLs. You should see the latest uploaded animal picture (cat, dog, or bear).
**Important: Port 8080 is the default port configured in the Docker setup. If you have changed the port, make sure to use the updated port when testing.**

```bash
https://localhost:8080/ (redirection to /fetch)
```
or 

```bash
https://localhost:8080/fetch
```



