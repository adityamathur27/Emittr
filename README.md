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
