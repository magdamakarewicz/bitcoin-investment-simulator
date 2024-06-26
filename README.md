# Bitcoin Investment Simulator

## Overview

Bitcoin Investment Simulator is a Spring application that allows users to calculate potential earnings from a historical
Bitcoin investment. Users can input a past date and investment amount, and the app will fetch historical and current BTC
prices to calculate and display potential profit or loss.

**Note:** The code is fully commented (documentation within the code). However, this was done as an exercise, 
and I advocate for code that is self-explanatory through method names and structure, as it is generally more readable.

Additionally, I deliberately did not use Spring Boot, but instead included configuration for different profiles within 
a configuration class. This approach was chosen to demonstrate an alternative to the simplest method and to show that 
I understand how to handle profiles in a different approach.

## Table of Contents

- [Features](#features)
- [Known Issues](#known-issues)
- [Screenshots](#screenshots)
- [Documentation](#documentation)
- [Technologies](#technologies)
- [Setup and Running the Project](#setup-and-running-the-project)
- [Environment Configuration](#environment-configuration)

## Features

- User input for historical date and investment amount in PLN/USD.
- Fetch BTC prices from CoinDesk API.
- Calculate potential earnings and percentage gain/loss.
- Save each calculation to a database.
- Different profiles for development and production environments.

## Known Issues

### CoinDesk API

The application relies on the CoinDesk API to fetch historical and current BTC prices. Currently, there are some issues
with the API which might cause errors in the application’s functionality. These issues are beyond the control of this
project and may affect the accuracy of the calculations and the overall performance of the application.

If you encounter any issues related to the CoinDesk API, please be aware that this is a known problem and is being
investigated. Potential fixes or workarounds will be implemented as they become available.

## Screenshots

Here is a screenshot of the application in action in the development environment:

![Investment Simulation](screenshots/screenshot.png)
*Investment Simulation*

## Documentation

This project includes detailed documentation within the code. Comments are provided to explain the functionality of each
class and method. This documentation can help you understand how the application works and guide you through extending
or modifying the code if necessary.

## Technologies

- Java 17
- Hibernate
- MySQL (or H2 for in-memory database)
- Maven

## Setup and Running the Project

### Prerequisites

- Java 17
- Maven 3.6+
- MySQL (for production profile)

### Running the Project

1. Clone the repository:
    ```sh
    git clone https://github.com/magdamakarewicz/bitcoin-investment-simulator.git
    cd bitcoin-investment-simulator
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Configuration Profiles The application uses different configuration profiles for different environments:

- `dev`: Development profile using H2 in-memory database.
- `test`: Testing profile using H2 in-memory database.
- `prod`: Production profile using MySQL database.

You can specify the active profile by using the `-Dspring.profiles.active` parameter or by setting
the `SPRING_PROFILES_ACTIVE` environment variable.

#### Development Environment

In development mode, the application uses an in-memory H2 database. Ensure you are in the project directory.

Configure your IDE to use the `dev` profile:

- In IntelliJ IDEA: Go to "Edit Configurations", add VM options `-Dspring.profiles.active=dev`.
- In Eclipse: Go to "Run Configurations", add VM arguments `-Dspring.profiles.active=dev`.

Run the main class `com.enjoythecode.bitcoininvestmentsimulator.app.BitcoinInvestmentSimulator` from your IDE.

#### Production Environment

In production mode, the application uses a MySQL database. Make sure MySQL is installed and running.

Create a MySQL database:

```sql
CREATE DATABASE bitcoininvestmentdb;
```

Update the `JpaConfig` class with your MySQL credentials if needed (not provided in this setup, but make sure it's
configured properly).

Configure your IDE to use the `prod` profile:

- In IntelliJ IDEA: Go to "Edit Configurations", add VM options `-Dspring.profiles.active=prod`.
- In Eclipse: Go to "Run Configurations", add VM arguments `-Dspring.profiles.active=prod`.

Run the main class `com.enjoythecode.bitcoininvestmentsimulator.app.BitcoinInvestmentSimulator` from your IDE.

4. Packaging the Application You can package the application into a runnable JAR file.

Build the JAR with dependencies:

```sh
mvn package
```

Run the JAR file with the desired profile (e.g., dev):

```sh
java -Dspring.profiles.active=dev -jar target/BitcoinInvestmentSimulator-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Environment Configuration

### Overview

In the project, various environments have been defined, including production, development, and testing. The
configuration for these environments is managed as follows:

### Production Environment

In the production environment, settings are defined directly within the methods of the `JpaConfig` class, leveraging
MySQL as the database.

### Development and Testing Environments

For development and testing environments, a combination of environment variables and the `application.properties` file
is utilized. This setup allows for flexible configuration of database access credentials, with H2 used as the database.

To modify the configuration for the development environment, adjustments should be made to the `application.properties`
file accordingly.