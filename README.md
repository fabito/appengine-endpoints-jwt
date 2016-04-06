# appengine-endpoints-jwt

A skeleton application for Google Cloud Endpoints in Java.
It exposes a token based authentication API using a custom JWT and uses Dagger 2 for DI.

* Google Cloud Endpoints
* Dagger 2 for dependency injection
* JWT custom authenticator (jose4j)
* Objectify

## Dagger 2 integration

### Initialization

The Dagger injector (object graph) is created in a specialized ServletContextListener
and put as an attribute in the ServletContext for further utilization.

### Injecting dependencies into @Apis

Api endpoints instantiation is still performed by the SystemServiceServlet via reflection.
After new instances are created the injector is retrieved from the ServletContext

## Custom JWT Authenticator

The JwtAuthenticator is a [Authenticator | http://t.com] implementation which retrieves the JWT token
from the Authorization header, performs claims validation and signature verification.
It also creates a User object which will be injected in the Api methods





### Testing the endpoints using the local development server

http://localhost:8080/_ah/api/explorer

 /usr/bin/google-chrome-stable --user-data-dir=test --unsafely-treat-insecure-origin-as-secure=http://localhost:8080

