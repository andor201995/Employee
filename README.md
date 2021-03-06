## INTRODUCTION
This Project is a sample Project to Implement Clean Coding and Following Coding Ethics.This sample project is having a very simple logic
of storing employee details and performing CRUD operation on the Employee.

### Prerequisite
 
   	1.	Core JAVA programing
    2.	Serlvel Programming and structure of Web Project
    3.	Web Cointainer (ex: TOMCAT(Servlet cointainer) & JETTY)
    4.	MONGODB
    5.	MAVEN life cycles
    6.	Eclipse 
    7.	Jersey Rest(JAX-RS)
    8.	Jackson(or GSON) to convert JSON to Object (vise versa)
    9.	Spring Core
 

### Motivation

I am a fresher in the field so to become a successfull JAVA developer i needed to work on sample project to understand good coding ethics and to apply new technology to my project.

*The main aim of project is not to apply complex buisness logic but only
to learn how to configure REST,JETTY,JACKSON,MAVEN,SPRING etc.*

### Installation

The installation of project is very Simple follow the below Steps:
1. clone the project to a new directory.
2. run **mvn clean package** (all the dependencies will get downloaded automatically)
3. run **mvn eclipse:eclipse**
4. import it as a maven project to eclipse 
5. start mongodb using command in terminal 

	   mongod --auth --port 5000 --dbpath ~/db/mongodb
6. configure your **mongodb** by creating a employee Db and employeeProfile and counters Collection by running following code
        
	   use employee
    	db.createUser(
   		   {
     			user: "admin_anmol",
     			pwd: "admin",
     			roles: [ "readWrite", "dbAdmin" ]
   		    }
	    )
	    db.counters.insert({"_id":"userid","seq":0})
	    db.employeeProfile.insert({"name":"anmol"})

   counters is used to sequential increment of _id (employee ID)
   close the mongodb and
7. now start mongodb using 
                
		         mongod --auth --port 5000 --dbpath ~/db/mongodb
8. create a DB.properties file to configure DB in your Project
        	example
	
	        MONGODB PROPERTIES
			DB_ID=admin_anmol
			DB_PASSWORD=admin
			DB_NAME=employee
			DB_PORT=5000
			HOST_NAME=localhost
9. now set VM Arguments in eclipse(-Dport is the port on which your webapp is running)

	   -Dport=8080 -Ddb_properties_file_path="/home/anmol/Workspace/Employee/src/main/resource/DB.properties"
  10. run the porject and use below REST endpoints to perform CRUD Actions:
  
	REQUEST-TYPE	Actions 			URI

	GET 			listofEmployee		localhost:8080/Employee/service/employee
	GET 			singleEmployee 		localhost:8080/Employee/service/employee/{id}
	POST			addEmployee			localhost:8080/Employee/service/addEmployee			
	DELETE			deleteEmployee		localhost:8080/Employee/service/deleteEmployee/{id}
	PUT 			updateEmployee		localhost:8080/Employee/service/updateEmployee
sample payload for PUT and POST("_id" is not needed as _id is auto incremented from program)
>
			{	"empId":"1",   			
			"firstName":"anmol",
			"lastName":"Srivastava",
			"designation":"JAVA Developer",
			"gender":"male"
			}
				

#### Reference
1. MAVEN:

	[Introduction video](https://javabrains.io/courses/)

	[REST using archetype](https://www.mkyong.com/webservices/jax-rs/jersey-hello-world-example/)

2. MONGODB:

	[CURD operatins](https://www.tutorialspoint.com/mongodb/mongodb_create_database.htm)
 			
	[Create Users in mongodb](https://docs.mongodb.com/manual/reference/method/db.createUser/)

	[MONGO JAVA Driver](https://www.tutorialspoint.com/mongodb/mongodb_java.htm)

	[Extra](http://www.mkyong.com/tutorials/java-mongodb-tutorials/)

3. REST:

	[Convert payload to Bean](https://stackoverflow.com/questions/17568469/jersey-2-0-equivalent-to-pojomappingfeature/34336456#34336456?newreg=e8351c3fe7394753bc5e5b6a486d7723)

4. JETTY:

	[Pointing WEB.xml](http://stackoverflow.com/questions/17246512/embedding-jetty-as-a-servlet-container)

	[JETTY+JERSEY](https://www.acando.no/thedailypassion/200555/a-rest-service-with-jetty-and-jersey)

	[Embedded Jetty](http://nikgrozev.com/2014/10/16/rest-with-embedded-jetty-and-jersey-in-a-single-jar-step-by-step/)

5. Jackson & Gson:

	[Gson Vs Jackson](http://www.doublecloud.org/2015/03/gson-vs-jackson-which-to-use-for-json-in-java/)

	[Ignore Unknown Property in Bean](http://stackoverflow.com/questions/4486787/jackson-with-json-unrecognized-field-not-marked-as-ignorable)

	[Using different context handlers](https://blog.jayway.com/2014/02/16/using-different-context-handlers-on-different-ports-in-jetty-9/)

6. Spring core:

	[Introduction](https://www.javatpoint.com/spring-tutorial)
	