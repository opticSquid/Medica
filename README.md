# Medica
<div align="center">
  <img src="./logo.png" alt="Logo Image" style="width:70%;object-fit: contain">
</div>

This is a project for building a modern open source microservice driven platform for a large health institution like a hospital where different aspects of daily functioning of the institution is brought under one roof in a scalable way using microservices and an event driven architecture to make the application scale well for future work loads and being readily available on cloud. We want to build a platform with its own marketplace where we can have some in house and some 3rd party plugin services which will extend and add to the capability of this platform given that the services maintain a common standard and can communicate with each other giving rise to seamless integreation and interoperability.Every customer can have their own add on services besides some basic default services that come built in. This whiole experience can be customized by the client by different plugins they choose which will tailor to their needs. Plugins can be both free of cost or can come with some cost dependig on the 3rd party service maker. In this repo our goal is to
- provide a stable core platform for the service makers and cluents to rely on
- give some basic default service set
- Create a proper maeket place with incentive for the service maker to develop this platform.
Developers will be developing for this platform because they will get monetary incentive
Clients will choose this platform because they can decide for what sexacty ervices they need to pay. As the whole code is publically available there will no questions rased on user data privacy.

## Stable Microservices ✔️

- Doctor Service
- Medical Service

## In Progress Microservices 🧪

- Appointment Service

## Architecture
All services are now built spring boot in java. But as the services are isolated you can build using any language.
**All microservices should communicate using Rabbit MQ / Kafka (to be decided later**

### Qualification criteria for a microservice to be added to the main project

- Should support both JSON and XML format (Content negotiation).
- Use Postgres DB
- Have an API documentation inside the service itself describing all its end points and the request, response schema
