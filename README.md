# Helsinki City Bike App

This application provides data about journeys made with city bikes in the Helsinki Capital area in summer 2021. Users can view basic information about bike journeys and stations, and get detailed information about stations from the listing, including the amount of departures and returns.

## Technologies

The Station and Journey App utilizes the following technologies:

- Spring Boot: Java framework for building robust and scalable web applications.
- MySQL: Relational database management system for storing and retrieving data.
- React: JavaScript library for building user interfaces.
- Redux: State management library for managing the application state.
- React Router: Library for handling routing in a React application.
- React Bootstrap: UI component library for building responsive and mobile-first applications.

## Prerequisites

Before running the project locally, I recommend installing the following prerequisites:

- Java 17
- MySQL 8
- Node.js v18

The project was made on Windows 11.

## Configuration

To configure the project, follow these steps:

1. Open the backend project in your preferred Java IDE.
2. Configure the database connection details by creating a `application.properties` file which you should save in the `src/main/resources` directory.
3. Make sure to update the following properties with your specific database information:
   - spring.datasource.url=<your datasource>
   - spring.datasource.username=<your username>
   - spring.datasource.password=<your password>
   - spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   - server.port=8081

## How to Run the Project

To run the project, follow these steps:

1. Build the backend application:
   - Open a terminal/command prompt in the root directory of the backend project.
   - Run the following command: `./mvnw clean install`
2. Start the backend server:
   - Run the following command: `./mvnw spring-boot:run`
3. Build the frontend application:
   - Open a terminal/command prompt in the root directory of the frontend project.
   - Run the following command: `npm install`
4. Start the frontend server:
   - Run the following command: `npm start`
5. Access the application in your web browser at `http://localhost:3000`.

## Todo

- Refactor the data importing to something more robust and improve data validation.
- Improve error handling.
- Add tests to both backend and frontend.
- Implement search functionality with filters and sorting options in frontend (already exists in backend).
- Implement pagination in frontend (already exists in backend).

