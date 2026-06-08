# BookstoreDXC
Bookstore REST API for DXC Assessment 
1. The solution is based on Java 20/Maven/SpringBoot REST API/H2 SQL DB
2. The REST service is built upon Springboot\
	a. The application automatically uploads the books.csv and authors.csv data files into the H2 DB on startup\
	b. The books.csv path is set in `/BookstoreDXC/src/main/resources/application.properties` file as:
	`books.csv.filePathAndName=C:\git\BookstoreDXC\src\main\resources\books.csv`\
	c. The authors.csv path is set in `/BookstoreDXC/src/main/resources/application.properties` file as:
	`authors.csv.filePathAndName=C:\git\BookstoreDXC\src\main\resources\authors.csv`\
	d. The activities are performed using HTTP methods e.g. @PostMapping i.e. HTTP POST using the `addBook()` method \
	e. The endpoint for this method is `/bookstore/addBook`
3. Java based H2 in-memory database is used to store and process data 
4. The unit tests are written in JUnit using @SpringBootTest
5. To build and test the application, run the maven build with the `mvn clean test` goal.
6. The application can be run by \
	a. To run the application, run the maven build with the `mvn clean spring-boot:run` goal \
	b. This would run the app on tomcat on localhost:8080 \
	c. Use a REST client like Swagger or Postman to access the service
7. To skip tests and run use: `mvn -DskipTests clean package && mvn -DskipTests spring-boot:run`
8. The API is documented using SpringDoc. It is running at http://localhost:8080/swagger-ui/index.html

<img width="1920" height="1128" alt="image" src="https://github.com/user-attachments/assets/7a678976-c049-46aa-8ac5-47b89658a98d" />

9. The username is `user` and password `password`
10. The find books endpoint is `/bookstore/public/find`. It is a public endpoint. All other endpoints are protected and need successful authentication to access them.
11. The API is protected using Spring Security with HTTP Basic Authentication. It is configured in `SecurityConfig` class.
12. `AuthenticationEvents` monitors all the authentication events

# Spring Boot Actuator
Spring Boot Actuator is at: `http://localhost:8080/actuator/health` \
The response is as:
<img width="1625" height="920" alt="image" src="https://github.com/user-attachments/assets/d163bcce-d78b-41dd-80fe-4d747041b521" />

# Use cases and Tests
## Integration Tests
Integration tests were done using Junit from a separate client app (BookstoreDXCTestingClient). 
The test results are shown below:
<img width="1920" height="1128" alt="image" src="https://github.com/user-attachments/assets/4a0a3343-eb4f-4685-a341-f0f8263c45ef" />

# Manual Tests
Manual Tests were done using OpenAPI. Test results are below.
# Add a new book
## Invalid ISBN
<img width="571" height="640" alt="image" src="https://github.com/user-attachments/assets/3b991fb9-c899-4a10-acd1-d0f34b72137d" />

<img width="631" height="296" alt="image" src="https://github.com/user-attachments/assets/c86dcda7-7ac8-492d-b55c-42f06f7ebd63" />

Server log: `ISBN field is invalid for book: Books [isbn=0, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

# Trying to add existing book
<img width="533" height="629" alt="image" src="https://github.com/user-attachments/assets/92df781e-7307-4b10-a8ef-1c0a3cdfa80c" />

<img width="669" height="304" alt="image" src="https://github.com/user-attachments/assets/9371f20a-b830-459f-8c02-433bf7d413f2" />

Server log: `Book with this ISBN already exists! Books [isbn=9780545010317, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

## Correct Request with 1 author

<img width="636" height="644" alt="image" src="https://github.com/user-attachments/assets/e9e71cf5-90b9-46e9-aa60-0c3762f8a8ae" />

<img width="535" height="323" alt="image" src="https://github.com/user-attachments/assets/5890a116-bd31-4147-ae0b-6e5cc124f6b5" />

Server log: `Added new book: Books [isbn=9780545019317, title=Title, authors=[authors], yearPublished=1110, price=110.1, genre=Fantasy]`

## Correct Request with multiple authors
<img width="585" height="663" alt="image" src="https://github.com/user-attachments/assets/30197509-c5f7-4081-b440-84e989c5082b" />

<img width="478" height="338" alt="image" src="https://github.com/user-attachments/assets/7440052e-85bd-44d3-9b98-5d3b0febbcc1" />

Server log: `Added new book: Books [isbn=9780545319317, title=title1, authors=[authors1, authors2], yearPublished=1110, price=110.1, genre=string]`

# Update an existing book
## Invalid ISBN 1
<img width="801" height="797" alt="image" src="https://github.com/user-attachments/assets/8f159df5-1ffd-476d-8a46-97378b3d00fb" />

<img width="567" height="246" alt="image" src="https://github.com/user-attachments/assets/b74fdc01-0360-4e35-8edd-b7ec1cf5d1a2" />

Server log: `ISBN field is invalid for book: Books [isbn=111, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

## Invalid ISBN 2
<img width="759" height="772" alt="image" src="https://github.com/user-attachments/assets/331a73fc-a5a5-4f92-b87e-d25ac45ff0cd" />

<img width="579" height="299" alt="image" src="https://github.com/user-attachments/assets/3a53eab1-591f-4d9e-85b5-9c196a598af9" />

Server log: `isbn field is invalid for book: Books [isbn=111, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

## Mismatch ISBN
<img width="820" height="778" alt="image" src="https://github.com/user-attachments/assets/7afe330a-f401-4207-bcc7-7ef68b3afa8a" />

<img width="605" height="254" alt="image" src="https://github.com/user-attachments/assets/6da357d7-6e9f-4985-a468-f1f65d82cb02" />

Server log: `isbn field is invalid for book: Books [isbn=9780545010316, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

## Incorrect ISBN
<img width="817" height="791" alt="image" src="https://github.com/user-attachments/assets/b8532e23-5a0f-4c5e-be85-b053895a0cc2" />

<img width="624" height="270" alt="image" src="https://github.com/user-attachments/assets/44279a83-d5c9-4c49-9553-9f7138351347" />

Server log: `Book with this ISBN does not exist! Books [isbn=9780545510317, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`
## Correct ISBN
<img width="857" height="782" alt="image" src="https://github.com/user-attachments/assets/76246556-1dc6-4d7d-8599-b4a9b44eb43d" />

<img width="541" height="367" alt="image" src="https://github.com/user-attachments/assets/4cfc0f5f-b0b9-4d5c-82be-2d55892fcf50" />

`Updated book: Books [isbn=9780545010313, title=title, authors=[authors], yearPublished=1110, price=1110.1, genre=genre]`

# Delete a book by ISBN, throws 404 if book not found
## Incorrect ISBN
<img width="762" height="394" alt="image" src="https://github.com/user-attachments/assets/93ab0dfb-ec22-4517-b0d2-a1d2cf461c86" />

<img width="622" height="302" alt="image" src="https://github.com/user-attachments/assets/d643d2e7-38e2-48b4-a50f-cefd1845a786" />

Server log: `Book with ISBN 9780545510313 not found for deletion`
## Correct ISBN
<img width="784" height="396" alt="image" src="https://github.com/user-attachments/assets/d982e11b-eb0c-40e5-9583-96ccabd1e450" />

<img width="827" height="381" alt="image" src="https://github.com/user-attachments/assets/739c79f8-ae88-4d22-8d79-1a82b38d108c" />

Server log: `Deleted book with ISBN: 9780545010316`

# Find books either by title or author name or both
## Incorrect Title
<img width="857" height="477" alt="image" src="https://github.com/user-attachments/assets/23829cd9-a16d-46f8-a187-59a054e8e949" />
<img width="642" height="257" alt="image" src="https://github.com/user-attachments/assets/58447865-d808-46fe-a357-69e849ab5734" />

Server log: `No books found with title: Harper Lee and author: null`
## Correct Title
<img width="829" height="477" alt="image" src="https://github.com/user-attachments/assets/f3fbfcaf-f2a4-495e-a4e0-c0be00cc70bd" />
<img width="634" height="355" alt="image" src="https://github.com/user-attachments/assets/491190d4-1d2c-4c67-a259-a0ff43fbf0a8" />

Server log: `Found book Books [isbn=9780060935467, title=To Kill a Mockingbird, authors=[Harper Lee], yearPublished=1960, price=9.99, genre=Classic Fiction] with title: To Kill a Mockingbird and author: [Harper Lee]`
## Incorrect Author
<img width="836" height="487" alt="image" src="https://github.com/user-attachments/assets/6f6cb845-2677-4169-ab70-cec28b366d8d" />
<img width="672" height="299" alt="image" src="https://github.com/user-attachments/assets/540c37cf-fb42-4d46-8399-b6ed0d90281c" />

Server log: `No books found with title: null and author: Correct Title`
## Correct Author
<img width="851" height="473" alt="image" src="https://github.com/user-attachments/assets/f2e023d4-b9b2-4595-b323-17a9290b3ec4" />
<img width="586" height="358" alt="image" src="https://github.com/user-attachments/assets/6e3f406a-6f8f-4bde-ba19-72d143d54f4e" />

Server log: `Found book Books [isbn=9780060935467, title=To Kill a Mockingbird, authors=[Harper Lee], yearPublished=1960, price=9.99, genre=Classic Fiction] with title: To Kill a Mockingbird and author: [Harper Lee]`

## Correct Title and Author
<img width="909" height="463" alt="image" src="https://github.com/user-attachments/assets/34bf4d61-5234-48b4-8192-e40c47d01f2e" />
<img width="531" height="367" alt="image" src="https://github.com/user-attachments/assets/f9495b31-3d5d-4842-900a-16ffb05c893a" />

Server log: `Found book Books [isbn=9780060935467, title=To Kill a Mockingbird, authors=[Harper Lee], yearPublished=1960, price=9.99, genre=Classic Fiction] with title: To Kill a Mockingbird and author: [Harper Lee]`



