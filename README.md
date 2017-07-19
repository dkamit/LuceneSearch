# LuceneSearch

Search Implementation with Lucene and SpringBoot

#Requirements

This application is based on maven.
You should install maven 3+ and Java8 to run this application.

##Building and Running

Method 1 : Run the application as mvn spring-boot:run
Method 2 : Run the application with following commands
            ```
            mvn clean install
            java -jar target/SearchApp-0.0.1-SNAPSHOT.jar
            ```
##Server

This app runs on Tomcat8, no need for an external tomcat since I am using embedded tomcat for running the application.
Advantages:
        1. Easy to deploy.
        2. Easy to build microservices in this way.
        3. Separation of Container server will clearly help us in identifying the issues associated with a particular service.
        4. Easy to debug and test since it's a standalone server deployments.

###Server Properties

By Default server start at port 8080 you can modify the port number by passing the command line argument like
        ```java -jar target/SearchApp-0.0.1-SNAPSHOT.jar --server.port=8081```
You can pass the index folder value as a command line argument as document.index.dir="New Location" this is where app will store all the indexed data.

##Testing the app

Refer Building and Running section for running the app.
Tomcat will be listening to http://localhost:8080/?# by default.
You have E-E integration tests in IndexingServiceSearchTest.java which covers all the integration.

##Remarks

For simplicity only search is exposed if required we can expose the indexing portion as well.
