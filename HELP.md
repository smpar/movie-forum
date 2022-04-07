# General Info

### Developed and tested environment

- Workstation: Linux Mint 20.2 Uma (also tested on Ubuntu 20.04.4 LTS)
- Java: openjdk 11.0.14.1 2022-02-08
- Javac: javac 11.0.14.1
- Maven: Apache Maven 3.6.3

### How to run

```
git clone  git@github.com:smpar/movie-rama.git
cd movie-rama
mvn spring-boot:run
```

Launch browser on http://localhost:8080

### How to test

```
cd movie-rama
./mvnw test
```