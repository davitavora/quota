
# Quota Application

This project is used to manage users and API Quota

## Tech Stack

- Java 21
- Spring Boot 3

## Run Locally

1. Open the project in IntellIJ IDEA.
2. Run file `src/test/java/io/vicarius/quota/TestQuotaApplication.java`

> When running the application from the mentioned file instead of the one present in the `main` folder, SpringBoot will automatically create and connect with the containers defined in the class `src/test/java/io/vicarius/quota/configuration/ContainerConfiguration.java`, increasing the development experience.

> If you want to test HTTP calls locally, just go to http://localhost:8080/swagger-ui/index.html and run the calls via Swagger UI.

### Customization

If you want to change the project settings locally, they are present in the `src/main/resources/application.properties` file.

#### Properties

> Each property has its respective environment variable that can be used to override the value at run time.

- **application.quota.limit (APPLICATION_QUOTA_LIMIT)**: The default quota limit that a user can use
- **application.day-time.start (APPLICATION_DAY_TIME_START)**: The time that should be considered as the start of the day, so that it is possible to decide which DataSource to use, MySQL or ElasticSearch
- **application.day-time.end (APPLICATION_DAY_TIME_END)**: The time that should be considered as the end of the day, so that it is possible to decide which DataSource to use, MySQL or ElasticSearch

> If you want to change any execution parameters simply set any of the environment variables mentioned in the [Properties](#properties) section.
