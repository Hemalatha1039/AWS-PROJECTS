# Deploy a Spring Boot Application in EC2 that Integrates with DynamoDB using IAM Role

This project demonstrates deploying a Spring Boot application on an AWS EC2 instance, integrating it with AWS DynamoDB using IAM roles for secure access.

## Getting Started

These instructions will help you set up the project on your local machine for development and testing.

### Prerequisites

You need to install the following:

- **Java 21** (Amazon Corretto or OpenJDK)
- **AWS CLI** (for managing AWS services)
- **An AWS EC2 instance** (with necessary security groups)
- **IAM Role with DynamoDB access**
- **SSH access to EC2**

Example installation:

```sh
sudo yum install java-21-amazon-corretto
sudo yum install maven
```

### Installing

A step-by-step guide to setting up the environment:

1. **Clone the repository:**
   ```sh
   git clone https://github.com/your-repo/springboot-dynamodb.git
   cd springboot-dynamodb
   ```

2. **Build the project:**
   ```sh
   mvn clean package
   ```

3. **Upload the JAR to EC2:**
   ```sh
   scp -i your-key.pem target/springboot-dynamoDB-0.0.1-SNAPSHOT.jar ec2-user@your-ec2-ip:/home/ec2-user/
   ```

## Deployment

### Run the Application in the Background
```sh
nohup java -jar /home/ec2-user/springboot-dynamoDB-0.0.1-SNAPSHOT.jar --server.port=8082 > app.log 2>&1 &
```

### Check Logs
```sh
tail -f app.log
```

### Open the Required Ports
Ensure port 8082 is open in the AWS Security Group inbound rules.

## Running the Tests

### Unit Tests
Run the tests using:
```sh
mvn test
```

### Break down into end-to-end tests

These tests verify the complete workflow of the application.

```sh
curl -X GET http://your-ec2-ip:8082/health
```

### And coding style tests

Ensure the code follows best practices using:

```sh
mvn checkstyle:check
```

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [AWS SDK for Java](https://aws.amazon.com/sdk-for-java/) - AWS integration
