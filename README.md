## Simple Email REST API

> A project that was born with the idea of being a microservice and has only one objective: sending emails

### This project was developed by THR Tecnologia for educational purposes only.

### Commercial use of this product should not be made.

## How to run ?

> You will need a MySQL version 8 database, I recommend using a docker container.

```bash
docker run -d -p 3306:3306 --name email_mysql8
-e MYSQL_DATABASE=emaildb
-e MYSQL_ROOT_PASSWORD={root_password}
-e MYSQL_RANDOM_ROOT_PASSWORD=true
-e MYSQL_USER={mysql_user}
-e MYSQL_PASSWORD={mysql_user_pass}
mysql:8
```

> Tip: you can map a volume to persist the data.

#### Now you can map these environment variables when running the application.

```
Technologies studied:

Spring Data JPA,
Flyway,
Docker
```
