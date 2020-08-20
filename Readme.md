## Building a Full Stack Merchant app with Spring Boot, Spring Security, JWT, React 

### Required software to run the application
 
  JAVA         11
  Apache MAVEN latest version
  NodeJs       latest version
  Docker       latest version
  Postman
## Steps to setup and start the Spring Boot Back end app (merchant-server)

1. **Clone the application**

	git clone https://github.com/sipkaabvbg/merchant.git

2. **Run the app** 

   2.1 MySQL server
   You must have MySQL server installed and started. The connection string to it must be added to the 
	application.property file in merchant-server project. 
	
   2.2 Tomcat server
   In directory merchant-server\src\main\resources\csv is located users.csv file.
   This file contains information about system administrators and merchants.
   During application startup, these users are inserted into the database.
   
   You can run the spring boot app by typing the following command -

	mvn spring-boot:run
 
   in merchant-server directory.
   
	The Tomcat server will start on port 8080.

	You can also package the application in the form of a `jar` file and then run it like so -

	mvn package
	
	There is API description on url http://localhost:8082/swagger-ui.html 
	You can perform operations only for users, for transactions is added JWT security according to the task and cannot be accessed.
   2.3 Web server
   You can run the web app by typing the following command -
	
	npm start
	
	in merchant-client directory.
	
	The app server will start on port 3000 and in the browser on localhost:3000/login the application can be accessed.
	To work as a merchant you can login to the application with user adidas and password adidas1.
	A merchant can add transactions and view already added transactions.
    To work as an administrator you can login with username tom and password tom1.
	The administrator can see all merchants, edit them and delete them.
	2.4 Crown job
	From the directory cron you can run crown job.
	
	## Steps to setup and start the application in the Docker environment
	
	You can run the mysql server, spring boot app, react app and cron job by typing the following command -
	
	docker-compose up
	
	The MySQL server will start on port 3306.
	The Tomcat server will start on port 8080.
	The app server will start on port 9090 and in the browser on localhost:9090/login the application can be accessed.
	The docker process starts the cron job automatically.
	
	## Application documentation
	
	There are comments in the merchant-server and they describ code behavior of the classes and methods.
	There is a very important package com.example.merchant.payment. The classes in it implement 
	Factory pattern to determine the type of transaction.
	There are comments and descriptions in the packages described below.
	com.example.merchant.api         
	com.example.merchant.batch
	com.example.merchant.config
	com.example.merchant.controller
	com.example.merchant.converter
	com.example.merchant.exception
	com.example.merchant.model
	com.example.merchant.repository
	com.example.merchant.request
	com.example.merchant.response
	com.example.merchant.security
	com.example.merchant.service
	
	## Test and verification
	In root folder there is merchant_test.postman_collection file with main test usecases.