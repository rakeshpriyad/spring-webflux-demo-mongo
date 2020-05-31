# Build Reactive Rest APIs with Spring WebFlux and Reactive MongoDB

Read the tutorial : https://www.callicoder.com/reactive-rest-apis-spring-webflux-reactive-mongo/

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. MongoDB - 3.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/callicoder/spring-webflux-reactive-rest-api-demo.git
```

**2. Build and run the app using maven**

```bash
cd spring-webflux-reactive-rest-api-demo
mvn package
java -jar target/webflux-demo-0.0.1-SNAPSHOT.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The server will start at <http://localhost:8080>.

## Exploring the Rest APIs

The application defines following REST APIs

```
1. GET /emps - Get All Employee
1. GET /depts - Get All Dept

2. POST /emps - Create a new Employee
2. POST /depts - Create a new Dept

3. GET /emps/{id} - Retrieve a Employee by Id
3. GET /depts/{id} - Retrieve a Dept by Id


4. PUT /emps/{id} - Update a Employee
4. PUT /depts/{id} - Update a Dept

5. DELETE /emps/{id} - Delete a Employee
5. DELETE /depts/{id} - Delete a Dept

6. GET /stream/emps - Stream Employee to a browser as Server-Sent Events
6. GET /stream/depts - Stream Dept to a browser as Server-Sent Events
```

## Running integration tests

The project also contains integration tests for all the Rest APIs. For running the integration tests, go to the root directory of the project and type `mvn test` in your terminal.

Functional Programming is under com.example.webfluxdemo.functionnal.

URL for functional programming:
http://localhost:8080/empList  # Get all emp list

#Create emp
http://localhost:8080/emp/create

{
  "id": 6,
  "name": "RR",
  "salary": 500
 }
 