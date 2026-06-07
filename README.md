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
8. The API is documented using SpringDoc. It is running at http://localhost:8080/swagger-ui/index.html
# Use cases
# Add a new book
<img width="585" height="78" alt="image" src="https://github.com/user-attachments/assets/5a931a45-8e0d-493b-8424-c36ba3ada990" />
<img width="286" height="197" alt="image" src="https://github.com/user-attachments/assets/54a2aa41-2259-4f89-b031-90b88c732a01" />
<img width="631" height="296" alt="image" src="https://github.com/user-attachments/assets/c86dcda7-7ac8-492d-b55c-42f06f7ebd63" />

` ISBN field is invalid for book: Books [isbn=0, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

<img width="294" height="194" alt="image" src="https://github.com/user-attachments/assets/18115266-ad69-4a7d-9fd8-f7ae190d6441" />

`: Authors field is not a valid JSON array for book: Books [isbn=9780545010315, title=string, authors=string, yearPublished=1000, price=0.1, genre=string]`
# Trying to add existing book
<img width="669" height="304" alt="image" src="https://github.com/user-attachments/assets/9371f20a-b830-459f-8c02-433bf7d413f2" />

`Book with this ISBN already exists! Books [isbn=9780545010317, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`




