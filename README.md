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

<img width="585" height="254" alt="image" src="https://github.com/user-attachments/assets/5a647135-efa4-42e7-a9e6-c4db0a7cbb4c" />

`: Authors field is not a valid JSON array for book: Books [isbn=9780545010315, title=string, authors=string, yearPublished=1000, price=0.1, genre=string]`
# Trying to add existing book
<img width="669" height="304" alt="image" src="https://github.com/user-attachments/assets/9371f20a-b830-459f-8c02-433bf7d413f2" />

`Book with this ISBN already exists! Books [isbn=9780545010317, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

<img width="356" height="272" alt="image" src="https://github.com/user-attachments/assets/b36ae9e1-be24-4b2b-99c5-07d0ecda45a7" />
<img width="530" height="325" alt="image" src="https://github.com/user-attachments/assets/04136ddd-26fc-4e83-ba51-dd913679095f" />

`Saving new book: Books [isbn=9780545019315, title=Title, authors=[authors1, authors2], yearPublished=1110, price=110.1, genre=Fantasy]`

# Update an existing book
<img width="780" height="593" alt="image" src="https://github.com/user-attachments/assets/2f190a5f-ddda-45b2-a653-d2ceac91d45a" />

<img width="567" height="246" alt="image" src="https://github.com/user-attachments/assets/b74fdc01-0360-4e35-8edd-b7ec1cf5d1a2" />

`ISBN field is invalid for book: Books [isbn=111, title=string, authors=[string], yearPublished=0, price=0.1, genre=string]`

<img width="756" height="563" alt="image" src="https://github.com/user-attachments/assets/1d0e49f0-fa3a-40cc-9511-57edf0a3f3f7" />
<img width="459" height="310" alt="image" src="https://github.com/user-attachments/assets/4f8dbb4c-7b4e-4377-912b-fc1b7c47fd51" />

` Updated book: Books [isbn=9780545010316, title=Title, authors=[Author1], yearPublished=1110, price=110.1, genre=Fantaay]`

# Delete a book by ISBN, throws 404 if book not found

<img width="775" height="400" alt="image" src="https://github.com/user-attachments/assets/d7e5e7ba-8fa0-4806-8ce4-4b9cf4ecd986" />
<img width="590" height="267" alt="image" src="https://github.com/user-attachments/assets/c34fb961-9b4b-41b8-b496-168d56f9278c" />

`Book with ISBN 9780545010411 not found for deletion`

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


