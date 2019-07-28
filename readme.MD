**Instructions**

To compile application just need java 8 and maven.

If you want to run in console way, execute `clean compile assembly:single` , go to target folder
and execute **java -jar movements-jar-with-dependencies.jar** you will some messages in console as
everything is ok, and you can test it with postman file included.

There are 3 tests types, integration, service and repository.

I  use libraries only for testing and json serialization, I have used basic  com.sun.net.httpserver.HttpServer just for this technical test with all its limitations.
