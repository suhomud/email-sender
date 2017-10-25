# email-sender project

This is a email service for sending emails to customers.

## Customization properties
Please, be sure that you set up all properties in ```application.yml``` file.
Needed property to set up:

* email.sender.user.from - _your email addres_
* email.sender.provider.sendgridApiKey - _https://app.sendgrid.com api key_

## How to run

This application is packaged as a war which has Tomcat 8 embedded.
 No Tomcat or JBoss installation is necessary. 
 You run it using the java -jar command.
 
 * Clone repository
 * JDK 1.8 and maven 3.x 
 * You can build project by ``` mvn clean install``` will run integration test
 * Once you have successful build you can run application by:<br/>
 `mvn spring-boot:run` uses `application.property/yml`<br/>
 `mvn spring-boot:run -Drun.profiles=local` uses `application-local.property/yml` (You should add local property in case of need)<br/>
 
Default port is ```8090```(customize it in application.yml). 
  Please, use ```http://localhost:8090/email-sender/swagger-ui.html``` to have a look at API with UI
  
  
  
  ## Useful links
  
* Test
  * [JsonPath](https://github.com/json-path/JsonPath) uses for checking the response for integration tests
  * [Integration Testing in Spring](http://www.baeldung.com/integration-testing-in-spring) examples how to implement integration tests
* Properties 
  * [configuration properties with validation](http://www.baeldung.com/configuration-properties-in-spring-boot)
