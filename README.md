# BookstoreDXC
Bookstore REST API for DXC Assessment 
1. The solution is based on Java 20/Maven/SpringBoot REST API
2. The REST service is assumed to be built upon Springboot
	a. First call HTTP GET /putnace to upload the NACE.csv into the H2 DB
	b. The NACE.csv path is set in /dbRESTService3/src/main/resources/application.properties file as:
	NACE.csv.filePathAndName=C:\\Users\\shari\\eclipse-workspace\\dbRESTService3\\src\\main\\resources\\NACE.csv
	c. The messages are queried using @GetMapping i.e. HTTP GET using the getNACE() method
	d. The endpoint for this method is "/naceservice/getnace/{order}"
3. Java based H2 in-memory database is used to store and process data 
4. The unit tests are written in JUnit
5. The tests can be run by the command "mvn clean test"
6. The application can be run by 
	a. First using the "mvn clean package" to build the war file
	b. This will create ..\eclipse-workspace\dbRESTService\target\dbRESTService-0.0.1-SNAPSHOT.war
	c. Next using the "mvn spring-boot:run" to deploy the war file on tomcat on localhost:8080
	d. Use a REST client like Swagger or Postman to access the service
7. To skip tests and run use: mvn -DskipTests clean package && mvn -DskipTests spring-boot:run
8. Swagger is running at http://localhost:8080/swagger-ui.html#
9. First call /putnace to upload the NACE.csv into the H2 DB
