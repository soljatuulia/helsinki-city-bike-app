# Helsinki City Bike App

This application provides data about journeys made with city bikes in the Helsinki Capital area in summer 2021. Users can view basic information about bike journeys and stations, and get detailed information about stations from the listing, including the amount of departures and returns.

## Used technologies

- Spring Boot: Java framework for building robust and scalable web applications.
- MySQL: Relational database management system for storing and retrieving data.
- React: JavaScript library for building user interfaces.
- Redux: State management library for managing the application state.
- React Router: Library for handling routing in a React application.
- React Bootstrap: UI component library for building responsive and mobile-first applications.

## Prerequisites

Before running the project locally, I recommend installing the following:

- Java 17
- MySQL 8
- Node.js v18

The project was made on Windows 11.

## Configuration

To configure the project, follow these steps:

1. Open the backend project in your preferred IDE or terminal.
2. Configure the database connection details by creating a `application.properties` file which you should save in the `src/main/resources` directory.
3. Update the following properties to your `application.properties` file with your specific MySQL database information:
   - spring.datasource.url=<your datasource>
   - spring.datasource.username=<your username>
   - spring.datasource.password=<your password>
   - spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   - server.port=8081
4. Download these four data sets and save them in the `src/main/resources` directory of the backend application.
   - [https://dev.hsl.fi/citybikes/od-trips-2021/2021-05.csv] (https://dev.hsl.fi/citybikes/od-trips-2021/2021-05.csv)
   - [https://dev.hsl.fi/citybikes/od-trips-2021/2021-06.csv] (https://dev.hsl.fi/citybikes/od-trips-2021/2021-06.csv)
   - [https://dev.hsl.fi/citybikes/od-trips-2021/2021-07.csv] (https://dev.hsl.fi/citybikes/od-trips-2021/2021-07.csv)
   - [https://opendata.arcgis.com/datasets/726277c507ef4914b0aec3cbcfcbfafc_0.csv] (https://opendata.arcgis.com/datasets/726277c507ef4914b0aec3cbcfcbfafc_0.csv)
5. Update the file paths in `backend/src/main/java/backend/services/JourneyService.java` to match the three first files which include journey data.
6. Update the file path in `backend/src/main/java/backend/services/StationService.java` to match the fourth file which includes station data.


## How to Run the Project

To run the project, follow these steps:

1. Build and run the backend application either in your preferred IDE:
   - From the `backend/src/main/java/backend/HelsinkiBikeApplication.java` file .
   Or in your terminal, in the root directory of the backend project:
   - First run `./mvnw clean install`, then `./mvnw spring-boot:run`.
   Be patient! Starting the backend application will start the data import to your database. This can easily take 20 minutes or more.
2. Build and start the frontend application:
   - Open a terminal in the root directory of the frontend project.
   - First run `npm install`, then `npm start`.
5. Access the application in your web browser at `http://localhost:3000`.

## Todo

- Refactor the data importing to something more robust and improve data validation.
- Improve error handling.
- Add tests to both backend and frontend.
- Implement search functionality with filters and sorting options in frontend (already exists in backend).
- Implement pagination in frontend (already exists in backend).

