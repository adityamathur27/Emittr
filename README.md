# Emitter Backend API
This is a Spring Boot application that provides a RESTful API for fetching questions based on difficulty and language ID, and checking if an answer is correct.

## Prerequisites
- Java 8 or higher
- Maven
- IntelliJ IDEA 2023.3.2 or any other IDE that supports Spring Boot

## Setup
1. Clone the repository to your local machine.
2. Open the project in your IDE.
3. Update the `application.properties` file with your database credentials.

## Running the Application
1. Navigate to the root directory of the project in your terminal.
2. Run the command `mvn spring-boot:run` to start the application.

## API Usage
The application runs on `http://localhost:8080` by default. Replace `localhost` with your IP address if you're accessing the API from a different machine.

### Fetch Questions
To fetch questions based on difficulty and language ID, send a GET request to `/api/questions` with the `difficulty` and `languageId` parameters.
Example: `http://localhost:8080/api/questions?difficulty=1&languageId=1`

### Check Answer
To check if an answer is correct, send a GET request to `/api/questions/check-answer` with the `languageId`, `questionId`, and `selectedOption` parameters.
Example: `http://localhost:8080/api/questions/check-answer?languageId=1&questionId=1&selectedOption=A`

## Contributing
Please read [CONTRIBUTING.md](./CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.
## License
This project is licensed under the MIT License - see the [LICENSE.md](./LICENSE.md) file for details

# Emittr Quiz Application (Android Frontend) 
Emittr is a quiz application developed in Java and Kotlin using Android Studio. The application allows users to answer questions based on different languages and difficulties.
## Features
- Select a language and difficulty level to fetch questions.
- Answer the questions and submit your answers.
- Points are awarded for correct answers and stored using Shared Preferences.
- User progress can be viewed in the UserprogressFragment.
## Setup
1. Clone the repository.
2. Open the project in Android Studio.
3. Run the application on an emulator or physical device.
## Configuration
The base URL for the API is stored in the `res/values/config.xml` file. To change the base URL, follow these steps:
1. Open the `res/values/config.xml` file.
2. Find the string resource with the name `api_base_url`.
3. Replace the value of this string resource with your API base URL.
Here's an example of what the `config.xml` file might look like:
In this example, http://192.168.29.196:8080/api is the base URL for the API. Replace this URL with the base URL for your API.  After changing the base URL, the application will make all API calls to this new base URL.  
Dependencies
## OkHttp: For making network requests.
1.AndroidX: For using the latest Android components.
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.  
## License
MIT
## In this example, http://192.168.29.196:8080/api is the base URL for the API. Replace this URL with the base URL for your API.  After changing the base URL, the application will make all API calls to this new base URL.  
