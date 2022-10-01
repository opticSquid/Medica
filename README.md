# Medica

This is a project for building a modern event driven platform for a large health institution like a hospital where different aspects of daily functioning of a hospital is brought under one roof in a scalable way using microservices and an event driven architecture to make the application scale well for future work loads and being readily available on cloud.

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
