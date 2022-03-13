#Text-Api

Text Analyzer

This is an API Rest created with Spring Boot.

With this API, you can insert a sentence, then the substring chars quantity, and it will be analyzed by its substrings and how many times it repeats on all the sentence.

It handles pagination, filtering and custom exceptions messages.

POSTMAN Doc with endpoints: https://documenter.getpostman.com/view/16169993/UVsJw75t

On the /target folder is a fat-jar with an embedded Tomcat server (the default port is 8080), so you just open the command console on that folder and execute: java -jar challenge-0.0.1-SNAPSHOT.jar

No authentication needed.

