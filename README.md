# Bitcoin Investment Simulator

## Overview
Bitcoin Investment Simulator is a Spring application that allows users to calculate potential earnings from a historical Bitcoin investment. Users can input a past date and investment amount, and the app will fetch historical and current BTC prices to calculate and display potential profit or loss.

## Features
- User input for historical date and investment amount in PLN/USD.
- Fetch BTC prices from CoinDesk API.
- Calculate potential earnings and percentage gain/loss.
- Save each calculation to a database.
- Different profiles for development and production environments.

## Technologies
- Java 17
- Hibernate
- MySQL (or H2 for in-memory database)
- Maven
