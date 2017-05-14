# soewanto-gcd

Created by: Soewanto Anggono

### Configuration: ###

- Configuration file is in 
  gcd-prog-ejb/src/main/java/com/company/core/common/CommonConstant.java
  
- Two jms queues are:
  - java:jboss/exported/jms/listint
  - java:jboss/exported/jms/listgcd
  
- The standalone file is included containing jms queues configuration. However, username and password have to be configured separately on the server.

- This code is tested in wildfly-10.1.0.Final server

### Compiling prerequisite: ###

- Server has to be up and running, otherwise, the unit testing will be failed
- Alternatively, removing unit testing for jms queue will solve this issue

### How to compile: ###

- mvn install

### How to deploy: ###

- Deploy the ear file generated in gcd-prog-ear/target folder after compiling the code

### List of commands for rest web service (paste these commands in the browser) ###

- http://localhost:8080/gcd-prog-rest/push?i1=16&i2=20
- http://localhost:8080/gcd-prog-rest/list

### How to run soap web service: ###

- using chrome with Wizdler extension
- using soap ui

### Soap UI WSDL url: ###

- http://localhost:8080/gcd-prog-web/GcdService?wsdl

### List of soap web service requests: ###

- gcd
- gcdList
- gcdSum
