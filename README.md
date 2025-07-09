# Spring Boot Kafka Publish-Subscribe Quickstart

<img width="5600" height="3152" alt="Image" src="https://github.com/user-attachments/assets/89393edf-e163-48df-b0d5-08be9be8d672" />

---

## 🌟 Features & Concepts Demonstrated

This repository provides a hands-on quickstart guide to implementing **Apache Kafka**'s publish-subscribe messaging pattern using **Spring Boot**. It demonstrates how to set up separate producer (publisher) and consumer applications, communicate through a shared data transfer object, and manage Kafka locally.

* **Apache Kafka Fundamentals:** Understand topics, producers, and consumers.
* **Spring for Apache Kafka:** Seamless integration of Kafka messaging into Spring Boot applications.
* **Publish-Subscribe Pattern:** Clear separation of concerns between message publishers and consumers.
* **Asynchronous Communication:** Real-time data processing without blocking.
* **Loose Coupling:** Independent services communicating via events.
* **Shared DTO Module:** Best practice for defining common data structures across services.
* **Local Kafka Setup:** Instructions for running Kafka using both Open Source Apache Kafka and Confluent Community Edition.

---

## 🚀 Getting Started

To get this project up and running locally, follow these steps.

### Prerequisites

Before you begin, ensure you have the following installed:

* **Java Development Kit (JDK) 17 or higher**
* **Maven 3.6.0 or higher** (or Gradle if you prefer, though this project uses Maven)
* **Apache Kafka:** You'll need a running Kafka instance. Instructions for local setup are provided below.

### ⚙️ Setting Up Kafka Locally

You have two main options for running Kafka locally, for this project i followed the option 01:

#### Option 1: Open Source Apache Kafka

1.  **Download Kafka:**
    Visit the [Apache Kafka downloads page](https://kafka.apache.org/downloads) and download the latest stable release.
2.  **Extract the Archive:**
    Unzip the downloaded `kafka_*.tgz` file to a directory of your choice (e.g., `C:\kafka`, `~/kafka`).
3.  **Start Zookeeper:**
    Kafka relies on Zookeeper. Open a new terminal in your Kafka installation directory and run:
    ```bash
    # For Windows
    .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

    # For macOS/Linux
    ./bin/zookeeper-server-start.sh ./config/zookeeper.properties
    ```
4.  **Start Kafka Server:**
    Open *another* new terminal in your Kafka installation directory and run:
    ```bash
    # For Windows
    .\bin\windows\kafka-server-start.bat .\config\server.properties

    # For macOS/Linux
    ./bin/kafka-server-start.sh ./config/server.properties
    ```

#### Option 2: Confluent Community Edition (Recommended for ease of use)

Confluent Community Edition simplifies local Kafka setup significantly.

1.  **Download Confluent CLI:**
    Follow the instructions on the [Confluent documentation](https://docs.confluent.io/platform/current/installation/install/cli-install.html) to install the Confluent CLI.
2.  **Start Confluent Platform (including Kafka and Zookeeper):**
    Open a terminal and run:
    ```bash
    confluent local services start
    ```
    This command will start Zookeeper, Kafka, Schema Registry, and other components.

### 📝 Creating Kafka Topic(s)

Once Kafka is running, you need to create the topic(s) that your publisher will write to and your consumer will read from.

For this project, let's assume we'll use a topic named `orders-topic`.

```bash
# Using Apache Kafka CLI (from your Kafka installation directory)
# For Windows
.\bin\windows\kafka-topics.bat --create --topic orders-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

# For macOS/Linux
./bin/kafka-topics.sh --create --topic orders-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

# Using Confluent CLI
confluent kafka topic create orders-topic
````

You can verify the topic creation:

```bash
# Apache Kafka CLI
# For Windows
.\bin\windows\kafka-topics.bat --describe --topic orders-topic --bootstrap-server localhost:9092

# For macOS/Linux
./bin/kafka-topics.sh --describe --topic orders-topic --bootstrap-server localhost:9092

# Confluent CLI
confluent kafka topic describe orders-topic
```

### 🏃 Running the Spring Boot Applications

Each component (`consumer`, `publisher`, `shared-dto`) is a separate Maven module.

1.  **Clone the Repository:**

    ```bash
    git clone [https://github.com/moshdev2213/springboot-kafka-pubsub-quickstart.git](https://github.com/moshdev2213/springboot-kafka-pubsub-quickstart.git)
    cd springboot-kafka-pubsub-quickstart
    ```

2.  **Build the Project:**
    Build all modules to ensure dependencies are resolved and `shared-dto` is available for `publisher` and `consumer`.

    ```bash
    mvn clean install
    ```

3.  **Run the Consumer Application:**
    Open a new terminal, navigate to the `consumer` directory, and run the application.

    ```bash
    cd consumer
    mvn spring-boot:run
    ```

    The consumer will start listening for messages on the `orders-topic`.

4.  **Run the Publisher Application:**
    Open *another* new terminal, navigate to the `publisher` directory, and run the application.

    ```bash
    cd publisher
    mvn spring-boot:run
    ```

    The publisher application will expose an endpoint to send messages. Check the `publisher` module's Controller code for how to trigger message sending.

-----

## 💡 How It Works
---
<img width="4320" height="2172" alt="Image" src="https://github.com/user-attachments/assets/721f7ded-c723-4709-8441-472ac93e63fa" />


---

  * The **`publisher`** application is responsible for creating and sending messages (events) to the Kafka `orders-topic`. These messages typically represent an "order" or a similar business event.
  * The **`consumer`** application continuously listens to the `orders-topic`. Whenever a new message arrives, the consumer processes it (e.g., logs it, stores it in a database, triggers another business logic).
  * The **`shared-dto`** module defines the common data structure (e.g., `OrderDTO`) that both the publisher sends and the consumer expects, ensuring type safety and consistency across the message flow.

-----

## 📂 Project Structure

```
springboot-kafka-pubsub-quickstart/
├── consumer/                 # Spring Boot application for consuming Kafka messages
│   └── src/main/java/.../ConsumerApplication.java
│   └── ...
├── publisher/                # Spring Boot application for publishing Kafka messages
│   └── src/main/java/.../PublisherApplication.java
│   └── ...
├── shared-dto/               # Common Data Transfer Objects (DTOs) shared by consumer and publisher
│   └── src/main/java/.../OrderDTO.java
│   └── ...
└── README.md                 # This file
```

-----

## 🛠️ Technologies Used

  * **Java 17+**
  * **Spring Boot 3.x**
  * **Apache Kafka**
  * **Maven**

-----

## 🚀 What's Next? (Future Plans)

This quickstart is just the beginning\! We have exciting plans to enhance this project:

  * **Dockerization:** Containerize both the `publisher` and `consumer` Spring Boot applications using Docker, making them portable and easy to deploy.
  * **Docker Compose Integration:** Introduce a `docker-compose.yml` file to orchestrate the entire environment, including Kafka, Zookeeper, and our Spring Boot applications, for simplified local development and testing.
  * **CI/CD Pipeline:** Implement a Continuous Integration/Continuous Deployment (CI/CD) pipeline (e.g., using GitHub Actions, Jenkins, GitLab CI) to automate testing, building, and deployment processes.
  * **Cloud Deployment:** Explore deploying the Dockerized applications to a cloud environment like an AWS EC2 instance or any other Virtual Machine (VM) running Docker, demonstrating a real-world deployment scenario.

-----

## 🤝 Contributing

Contributions are welcome\! If you have suggestions for improvements, new features, or bug fixes, please feel free to:

1.  Fork the repository.
2.  Create a new branch (`git checkout -b feature/your-feature-name`).
3.  Make your changes.
4.  Commit your changes (`git commit -m 'feat: Add new feature'`).
5.  Push to the branch (`git push origin feature/your-feature-name`).
6.  Open a Pull Request.

-----


## 🙏 Acknowledgements

  * Apache Kafka Community
  * Spring Framework Contributors


## Open Source Kafka Startup in local ##

1. Start Zookeeper Server

    ```sh bin/zookeeper-server-start.sh config/zookeeper.properties```

2. Start Kafka Server / Broker

    ```sh bin/kafka-server-start.sh config/server.properties```

3. Create topic

    ```sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic NewTopic --partitions 3 --replication-factor 1```

4. list out all topic names

    ``` sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --list ```

5. Describe topics
  
    ``` sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic NewTopic ```

6. Produce message

    ```sh bin/kafka-console-producer.sh --broker-list localhost:9092 --topic NewTopic```


7. consume message

    ``` sh bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic NewTopic --from-beginning ```


## Confluent Kafka Community Edition in local ##

1. Start Zookeeper Server

    ```bin/zookeeper-server-start etc/kafka/zookeeper.properties```

2. Start Kafka Server / Broker

    ```bin/kafka-server-start etc/kafka/server.properties```

3. Create topic

    ```bin/kafka-topics --bootstrap-server localhost:9092 --create --topic NewTopic1 --partitions 3 --replication-factor 1```

4. list out all topic names

    ``` bin/kafka-topics --bootstrap-server localhost:9092 --list ```

5. Describe topics
  
    ``` bin/kafka-topics --bootstrap-server localhost:9092 --describe --topic NewTopic1 ```

6. Produce message

    ```bin/kafka-console-producer --broker-list localhost:9092 --topic NewTopic1```


7. consume message

    ```bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic NewTopic1 --from-beginning ```
    
8. Send CSV File data to kafka    

   ```bin/kafka-console-producer --broker-list localhost:9092 --topic NewTopic1 <bin/customers.csv```
   
   