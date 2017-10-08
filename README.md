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
 * You can run the project by running ``` mvn clean install```
 * Once you have successful build you can run application by:
 ```mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"```
 
Default port is ```8090```(customize it in application.yml). 
  Please, use ```http://localhost:8090/email-sender/swagger-ui.html``` to have a look at API with UI

