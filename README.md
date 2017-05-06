## INTRODUCTION
This Project is a sample Project to Implement Clean Coding and Following Coding Ethics.This sample project is having a very simple logic
of storing employee details and performing CRUD operation on the Employee.

##Prerequisite 
*	==>>Core JAVA programing
*	==>>Serlvel Programming and structure of Web Project
*	==>>Web Cointainer (ex: TOMCAT(Servlet cointainer) & JETTY)
*	==>>MONGODB
*	==>>MAVEN life cycles
*	==>>Eclipse 
*	==>>Jersey Rest(JAX-RS)
*	==>>Jackson(or GSON) to convert JSON to Object (vise versa)

## Motivation

I am a fresher in the field so to become a successfull JAVA developer i needed to work on sample project to get know to good 
coding ethics and to apply new technology to my project.The main aim of project is not to apply complex buisness logic but only
to learn how to configure REST,JETTY,JACKSON,MAVEN,SPRING etc.

## Installation

The installation of project is very Simple follow the below Steps:
1. clone the project to a new directory.
2. run **mvn clean package** (all the dependencies will get downloaded automatically)
3. run **mvn eclipse:eclipse**
4. import it as a maven project to eclipse 
5. start mongodb using command in terminal 
	```
		mongod --auth --port 5000 --dbpath ~/db/mongodb
	```
6.configure your **mongodb** by creating a employee Db and employeeProfile and counters Collection by running following code
	```
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
	```
	counters is used to sequential increment of _id (employee ID)
close the mongodb and
6. now start mongodb using 
	```
		mongod --auth --port 5000 --dbpath ~/db/mongodb

	```
7. create a DB.properties file to configure DB in your Project
	example
			```
			#MONGODB PROPERTIES
			DB_ID=admin_anmol
			DB_PASSWORD=admin
			DB_NAME=employee
			DB_PORT=5000
			HOST_NAME=localhost
			```
8.now set VM Arguments in eclipse
	```
	-Dport=8080 -Ddb_properties_file_path="/home/anmol/Workspace/Employee/src/main/resource/DB.properties"
	```
-Dport is the port on which your webapp is running
9.run the porject and use below REST endpoints to perform CRUD Actions:
	REQUEST-TYPE	Actions 			URI

	*GET 			listofEmployee		localhost:8080/Employee/service/employee
	*GET 			singleEmployee 		localhost:8080/Employee/service/employee/{id}
	*POST			addEmployee			localhost:8080/Employee/service/addEmployee			
	*DELETE			deleteEmployee		localhost:8080/Employee/service/deleteEmployee/{id}
	*PUT 			updateEmployee		localhost:8080/Employee/service/updateEmployee

sample payload for PUT and POST("_id" is not needed as _id is auto incremented from program)
```
{	"_id":"1",   			
	"firstName":"anmol",
	"lastName":"Srivastava",
	"designation":"JAVA Developer",
	"gender":"male"
}
```			

## Reference
1. ###MAVEN:
	-(Introduction video)
		-https://javabrains.io/courses/buildsys_mavenintro
	-(REST using archetype)
		-https://www.mkyong.com/webservices/jax-rs/jersey-hello-world-example/

2. ###MONGODB:

	-(CURD operatins)
		-https://www.tutorialspoint.com/mongodb/mongodb_create_database.htm
 			
	-(Create Users in mongodb)
		-https://docs.mongodb.com/manual/reference/method/db.createUser/

	-(MONGO JAVA Driver)
		-https://www.tutorialspoint.com/mongodb/mongodb_java.htm

	-(ALL in one Link)
		-http://www.mkyong.com/tutorials/java-mongodb-tutorials/

3. ###REST:

	-(to convert payload to POJO) 			
		-https://stackoverflow.com/questions/17568469/jersey-2-0-equivalent-to-pojomappingfeature/34336456#34336456?newreg=e8351c3fe7394753bc5e5b6a486d7723		


4. ###JETTY:

	*(pointing WEB.xml)
	http://stackoverflow.com/questions/17246512/embedding-jetty-as-a-servlet-container

	-(JETTY+JERSEY)
		-https://www.acando.no/thedailypassion/200555/a-rest-service-with-jetty-and-jersey
		-http://nikgrozev.com/2014/10/16/rest-with-embedded-jetty-and-jersey-in-a-single-jar-step-by-step/
