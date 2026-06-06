# BookstoreDXC
Bookstore REST API for DXC Assessment 
1. The solution is based on Java 20/Maven/SpringBoot REST API/H2 SQL DB
2. The REST service is built upon Springboot
	a. The application automatically uploads the books.csv and authors.csv data files into the H2 DB on startup
	b. The books.csv path is set in `/BookstoreDXC/src/main/resources/application.properties` file as:
	`books.csv.filePathAndName=C:\git\BookstoreDXC\src\main\resources\books.csv`
	c. The authors.csv path is set in `/BookstoreDXC/src/main/resources/application.properties` file as:
	`authors.csv.filePathAndName=C:\git\BookstoreDXC\src\main\resources\authors.csv`
	d. The activities are performed using HTTP methods e.g. @GetMapping i.e. HTTP GET using the getBookByTitle() method
	e. The endpoint for this method is "/bookstoreservice/getbookbytitle/{title}"
3. Java based H2 in-memory database is used to store and process data 
4. The unit tests are written in JUnit
5. To build and test the application, run the maven build with the `mvn clean test` goal.
6. The application can be run by 
	a. To run the application, run the maven build with the `mvn clean spring-boot:run` goal
	b. This would run the app on tomcat on localhost:8080
	d. Use a REST client like Swagger or Postman to access the service
7. To skip tests and run use: `mvn -DskipTests clean package && mvn -DskipTests spring-boot:run`
8. The API is documented using SpringDoc. It is running at http://localhost:8080/api-docs
