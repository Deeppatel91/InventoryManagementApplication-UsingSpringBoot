
# ICE3

## Overview
This project demonstrates a containerized network environment utilizing Docker, API Gateway, Kafka, and circuit-breaker patterns. Postman is used for testing.

---

## Features Demonstrated

| **Feature**             | **Description**                                    | **Demonstration**                                                                                           | **Completed** |
|-------------------------|--------------------------------------------------|-----------------------------------------------------------------------------------------------------------|---------------|
| **Containerized Network** | A Dockerized container environment.               | Docker containers running interconnected microservices (e.g., `api-gateway`, `product-service`, `order-service`). | ✅             |
| **Postman Testing**      | API testing using Postman.                        | API endpoints tested for functionality, errors, and latency.                                               | ✅             |
| **Circuit-Breaker**      | Handling service unavailability and retries.      | - Failed response (service down).<br> - Latent response simulation.<br> - Retry mechanism demonstration.   | ✅             |
| **Kafka Integration**    | Event-driven architecture with Apache Kafka.      | - `order-service` acting as an event producer.<br> - `notification-service` acting as an event consumer.<br> - Mailtrap used to demonstrate email notification. | ✅             |

---

## Detailed Demonstrations

| **Scenario**            | **Description**                       | **Steps & Tools Used**                                                                 | **Completed** |
|--------------------------|---------------------------------------|---------------------------------------------------------------------------------------|---------------|
| **Failed Response**      | Simulate a service-down scenario.     | - Shut down `product-service`.<br> - Postman request triggers circuit-breaker response. | ✅             |
| **Latent Response**      | Simulate slow service response.       | - Introduce latency in `order-service`.<br> - Observe fallback response via circuit-breaker. | ✅             |
| **Retry Mechanism**      | Demonstrate circuit-breaker retry.    | - Configure retry settings.<br> - Postman to test retries after failure.              | ✅             |
| **Kafka Producer**       | `order-service` produces events.      | - Place an order.<br> - Kafka topic receives event.                                   | ✅             |
| **Kafka Consumer**       | `notification-service` consumes events. | - Event triggers email notification.<br> - View email in Mailtrap.                    | ✅             |

---

## Tools & Technologies
- **Docker**: Containerized microservices.
- **Postman**: API testing.
- **Apache Kafka**: Event-driven messaging.
- **Resilience4j**: Circuit-breaker implementation.
- **Mailtrap**: Email testing.
