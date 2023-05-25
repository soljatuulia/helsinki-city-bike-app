# :bicyclist: Helsinki City Bike App

This application provides data about journeys made with city bikes in the Helsinki Capital area in summer 2021 as well as the city bike stations.

Users can view basic information about bike journeys and stations, and get detailed information about stations from the listing, including the amount of departures and returns.

## :sparkles: Used technologies

- Java
- Spring Boot
- JUnit
- Mockito
- MySQL
- JavaScript
- React
- Redux
- React Router
- React Bootstrap
- Cypress

## :floppy_disk: Prerequisites

Before running the project locally, I recommend installing the following:

- [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [MySQL 8](https://dev.mysql.com/downloads/)
- [Node.js v18](https://nodejs.org/en/download)

The project was made  on Windows 11.

## :crystal_ball: Configuration

1. Create an empty MySQL database with your preferred name, for example `bike_app_db`.
1. Open the backend project in your preferred IDE or terminal.
2. Configure the database connection details by creating a `application.properties` file which you should save in the `src/main/resources` directory.
3. Update the following properties to your `application.properties` file with your specific MySQL database information. Remember to include your database name in your datasource.
   - spring.datasource.url=`<your datasource>`
   - spring.datasource.username=`<your username>`
   - spring.datasource.password=`<your password>`
   - spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   - server.port=8081
4. Download these four data sets and save them in the `src/main/resources` directory of the backend application.
   - https://dev.hsl.fi/citybikes/od-trips-2021/2021-05.csv
   - https://dev.hsl.fi/citybikes/od-trips-2021/2021-06.csv
   - https://dev.hsl.fi/citybikes/od-trips-2021/2021-07.csv
   - https://opendata.arcgis.com/datasets/726277c507ef4914b0aec3cbcfcbfafc_0.csv
5. Update the file paths in `backend/src/main/java/backend/services/JourneyService.java` to match the three first files which include journey data.
6. Update the file path in `backend/src/main/java/backend/services/StationService.java` to match the fourth file which includes station data.


## :zap: How to Run the Project

1. Build and run the backend application:
   - Either in your preferred IDE from the `backend/src/main/java/backend/HelsinkiBikeApplication.java` file .
   - Or in your terminal, in the root directory of the backend project. First run `./mvnw clean install`, then `./mvnw spring-boot:run`.
2. Be patient! Starting the backend application will start the data import to your database. This can easily take 20 minutes or more.
3. Build and start the frontend application:
   - Open a terminal in the root directory of the frontend project.
   - First run `npm install`, then `npm start`.
4. Access the application in your web browser at `http://localhost:3000`.

## :muscle: How to Run Tests

- Backend tests: I recommend running these in your IDE. No setup required.
- E2E tests: 
   1. Start both backend and frontend applications.
   2. In your terminal, go to the root directory of the frontend project and run `npm run cypress:open`.
   3. When Cypress opens, choose E2E Testing.
   4. Choose your browser.
   5. Coose `helsinki_bike_app.cy.js` and run tests.

## :thought_balloon: To Do

Given more time, I would definately work on the following:

- Refactor the data importing to something more robust and improve data validation.
- Improve error handling.
- Add tests to both backend and especially frontend, improve E2E testing.
- Add styles to UI.

