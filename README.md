# Coffee House Ordering System

## Overview
A simple console-based Java application that lets users order coffee from a menu, calculates the total bill, and saves customer details (name, phone number) along with the total order amount to a MySQL database.

## Features
- Display coffee menu with prices.
- Multiple coffee orders with quantities.
- Calculates total bill.
- Saves order details to MySQL database securely using JDBC.

## Technologies Used
- Java (JDK 8+)
- MySQL database
- JDBC (Java Database Connectivity)
- MySQL Connector/J driver

## Prerequisites
- Java Development Kit (JDK) installed
- MySQL Server installed and running
- Basic command line/terminal knowledge

## Download MySQL Connector/J
Download the latest MySQL Connector/J `.jar` file from the official [MySQL website](https://dev.mysql.com/downloads/connector/j/) and place it in your project directory.

## Compile and Run the Program
Open command prompt or terminal and run:

```bash
javac -cp ".;mysql-connector-java-8.3.0.jar" CoffeeHouse.java
java -cp ".;mysql-connector-java-8.3.0.jar" CoffeeHouse
