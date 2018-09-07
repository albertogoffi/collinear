# Collinearity Problem

The application is implemented as a Spring Boot application, using Kotlin and Gradle.

The application does not persist point in the space, i.e., the space has to be recreated every time the application is
 started.

## Start the Application

To start the application run the command: `./gradlew bootRun`

## Test the Application

Start the application running the command: `./gradlew bootRun`. Then requests can be made with `curl` to the server 
`localhost:8080`.

### Requests Examples

- Get all the points in the space

    ```curl http://localhost:8080/space```

- Add one point to the space

    ```curl -X POST -H 'Content-Type: application/json' -d '{ "x": 0.0, "y": 0.0 }' http://localhost:8080/point```
    
- Delete all the points in the space
    
    ```curl -X DELETE http://localhost:8080/space```

- Get all the line segments passing through at least N points

    ```curl http://localhost:8080/lines/N```

## Code Structure

The source code is contained in [src/main/kotlin/alberto.exercise](src/main/kotlin/alberto/exercise).
The code is organized in three files:

1) `Application.kt`: Spring boot application.
2) `SpaceController.kt`: Controller that handles requests.
3) `Model.kt`: Classes and algorithms.

Tests can be found in [src/test/kotlin/alberto.exercise](src/test/kotlin/alberto/exercise).