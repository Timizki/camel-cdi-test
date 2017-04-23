#Simple Apache Camel test project with CDI

Configures Apache Camel route that takes input from scheduled Singleton EJB Bean.
Route process message in multicast mode, so each endpoint and processors gets copy of orginal message. This way
we don't have to return the same object back to process.

Project uses following Camel dependencies
- `camel-core` for Apache camel itself
- `camel-cdi` for CDI integration
- `camel-jaxb` for marshalling
- `camel-restlet` for REST

##Route
Route contains:
- CDI bean consumer
- Two calls to REST services that consumes APPLICATION/XML
- Marshalling with Jaxb for the REST calls
- Pipelines for each phase of process
- Some processing done in processors
- Some logging

##Building
Project can be build just running `mvn install` at project root directory.

##Running
Project can be run at least on `Wildfly 10.1.0.Final` as is.

 