# BookstoreDXC
Bookstore REST API for DXC Assessment 
1. The solution is based on Java 20/Maven/SpringBoot REST API/H2 SQL DB
2. The REST service is built upon Springboot\
	a. The application automatically uploads the books.csv and authors.csv data files into the H2 DB on startup\
	b. The books.csv path is set in `/BookstoreDXC/src/main/resources/application.properties` file as:
	`books.csv.filePathAndName=C:\git\BookstoreDXC\src\main\resources\books.csv`\
	c. The authors.csv path is set in `/BookstoreDXC/src/main/resources/application.properties` file as:
	`authors.csv.filePathAndName=C:\git\BookstoreDXC\src\main\resources\authors.csv`\
	d. The activities are performed using HTTP methods e.g. @PostMapping i.e. HTTP POST using the addBook() method\
	e. The endpoint for this method is "/bookstore/addBook"\
4. Java based H2 in-memory database is used to store and process data 
5. The unit tests are written in JUnit using @SpringBootTest
6. To build and test the application, run the maven build with the `mvn clean test` goal.
7. The application can be run by 
	a. To run the application, run the maven build with the `mvn clean spring-boot:run` goal
	b. This would run the app on tomcat on localhost:8080
	d. Use a REST client like Swagger or Postman to access the service
8. To skip tests and run use: `mvn -DskipTests clean package && mvn -DskipTests spring-boot:run`
9. The API is documented using SpringDoc. It is running at http://localhost:8080/swagger-ui/index.html
# Use cases and Tests
# Add a new book
## Invalid ISBN
<img width="449" height="656" alt="image" src="https://github.com/user-attachments/assets/38fea116-cfad-47a6-8cc8-081b27f8485e" />

<img width="631" height="296" alt="image" src="https://github.com/user-attachments/assets/c86dcda7-7ac8-492d-b55c-42f06f7ebd63" />

` ISBN field is invalid for book: Books [isbn=0, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

# Trying to add existing book
<img width="573" height="636" alt="image" src="https://github.com/user-attachments/assets/d0a4aae5-c23d-49d6-bc4c-d5cbed4ca96e" />

<img width="669" height="304" alt="image" src="https://github.com/user-attachments/assets/9371f20a-b830-459f-8c02-433bf7d413f2" />

`Book with this ISBN already exists! Books [isbn=9780545010317, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

## Correct Request with 1 author

<img width="522" height="661" alt="image" src="https://github.com/user-attachments/assets/e8a60627-7312-4e78-bd08-792ea00fa092" />

<img width="450" height="326" alt="image" src="https://github.com/user-attachments/assets/2ac90334-60b6-42ab-923e-a4af315d6eb7" />

`Added new book: Books [isbn=9780545019317, title=Title, authors=[authors], yearPublished=1110, price=110.1, genre=Fantasy]`

## Correct Request with multiple authors
<img width="485" height="627" alt="image" src="https://github.com/user-attachments/assets/849909e1-9e6d-41c1-86c7-ebf398f0b314" />

<img width="453" height="346" alt="image" src="https://github.com/user-attachments/assets/96df1e70-b94f-4254-8e51-9ef9fc367118" />

`Saving new book: Books [isbn=9780545019315, title=Title, authors=[authors1, authors2], yearPublished=1110, price=110.1, genre=Fantasy]`

# Update an existing book
## Invalid ISBN 1
<img width="780" height="593" alt="image" src="https://github.com/user-attachments/assets/2f190a5f-ddda-45b2-a653-d2ceac91d45a" />
<img width="567" height="246" alt="image" src="https://github.com/user-attachments/assets/b74fdc01-0360-4e35-8edd-b7ec1cf5d1a2" />

`ISBN field is invalid for book: Books [isbn=111, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

## Invalid ISBN 2
<img width="846" height="795" alt="image" src="https://github.com/user-attachments/assets/c33c6535-3ee9-4ea0-a964-e59f3389af53" />
<img width="579" height="299" alt="image" src="https://github.com/user-attachments/assets/3a53eab1-591f-4d9e-85b5-9c196a598af9" />

`isbn field is invalid for book: Books [isbn=1110, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

## Mismatch ISBN
<img width="781" height="778" alt="image" src="https://github.com/user-attachments/assets/37c10bfb-7a2d-4196-945c-65eeb1e317c1" />
<img width="605" height="254" alt="image" src="https://github.com/user-attachments/assets/6da357d7-6e9f-4985-a468-f1f65d82cb02" />

`isbn field is invalid for book: Books [isbn=9780545010316, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

## Incorrect ISBN
<img width="732" height="780" alt="image" src="https://github.com/user-attachments/assets/3ed1f856-800b-408e-8e25-535528487d5e" />
<img width="624" height="270" alt="image" src="https://github.com/user-attachments/assets/44279a83-d5c9-4c49-9553-9f7138351347" />

`Book with this ISBN does not exist! Books [isbn=9780545018316, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`
## Correct ISBN
<img width="756" height="563" alt="image" src="https://github.com/user-attachments/assets/1d0e49f0-fa3a-40cc-9511-57edf0a3f3f7" />
<img width="459" height="310" alt="image" src="https://github.com/user-attachments/assets/4f8dbb4c-7b4e-4377-912b-fc1b7c47fd51" />

` Updated book: Books [isbn=9780545010316, title=Title, authors=[Author1], yearPublished=1110, price=110.1, genre=Fantaay]`

# Delete a book by ISBN, throws 404 if book not found
## Incorrect ISBN
<img width="775" height="400" alt="image" src="https://github.com/user-attachments/assets/d7e5e7ba-8fa0-4806-8ce4-4b9cf4ecd986" />
<img width="590" height="267" alt="image" src="https://github.com/user-attachments/assets/c34fb961-9b4b-41b8-b496-168d56f9278c" />

`Book with ISBN 9780545010411 not found for deletion`
## Correct ISBN
<img width="740" height="369" alt="image" src="https://github.com/user-attachments/assets/ad7aeb80-3767-40bb-81a1-33f6f095d018" />

<img width="813" height="338" alt="image" src="https://github.com/user-attachments/assets/9babd654-b609-473e-9219-1538d76c4384" />

`Deleted book with ISBN: 9780545010316`

# Find books either by title or author name or both
<img width="738" height="463" alt="image" src="https://github.com/user-attachments/assets/b8fc87fc-7ea2-44bf-b26d-b657f9864dd1" />
<img width="614" height="292" alt="image" src="https://github.com/user-attachments/assets/02b46a79-7840-4b33-b089-ae14ada6ee06" />
`No books found with title: Harper Lee and author: null`

<img width="733" height="465" alt="image" src="https://github.com/user-attachments/assets/50ea8a13-8e7f-43c5-a688-2f208f2362e0" />
<img width="562" height="325" alt="image" src="https://github.com/user-attachments/assets/f524ab7e-6eae-447b-b6a2-8bfe6ceab76e" />

`Found book Books [isbn=9780060935467, title=To Kill a Mockingbird, authors=[Harper Lee], yearPublished=1960, price=9.99, genre=Classic Fiction] with title: To Kill a Mockingbird and author: [Harper Lee]`

<img width="763" height="476" alt="image" src="https://github.com/user-attachments/assets/a366e6db-dcc5-4ce5-9d47-c3d1d89326b2" />

<img width="616" height="370" alt="image" src="https://github.com/user-attachments/assets/2bedbe5e-43c7-43e2-81fc-544d426876b9" />

`Found book Books [isbn=9780060935467, title=To Kill a Mockingbird, authors=[Harper Lee], yearPublished=1960, price=9.99, genre=Classic Fiction] with title: To Kill a Mockingbird and author: [Harper Lee]`


