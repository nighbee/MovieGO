MovieGo

MovieGo is an Android application that allows users to discover and explore a vast collection of movies. Built using Kotlin and utilizing The Movie Database (TMDb) API, this app provides a seamless and immersive movie experience.
Features

    Discover Movies: Browse through a vast collection of movies from various genres.
    Search: Search for movies by title, genre, or keywords.
    Movie Details: View detailed information about each movie, including ratings, release dates, runtime, and cast.
    Favorites: Save favorite movies for quick access.
    User Ratings: Rate and review movies based on personal preferences.
    Intuitive Interface: Enjoy a user-friendly interface that makes navigating and exploring movies a breeze.

Setup Instructions

    Clone the repository:

git clone https://github.com/your-username/MovieGo.git

    Open the project in Android Studio.

    Obtain an API key from The Movie Database (TMDb) by creating a new account.

    In the project, locate the Constants.kt file and replace the API_KEY value with your own API key.

    Build and run the app on an emulator or physical device.

API Documentation

MovieGo utilizes the TMDb API to fetch movie data. The following endpoints are used:
1. Get Popular Movies

GET /movie/popular

Returns a list of popular movies. This endpoint is used to fetch the popular movies displayed on the app's home screen.
2. Get Movie Details

GET /movie/{movie_id}

Returns detailed information about a specific movie, including its title, overview, release date, runtime, and cast.

For more information about the TMDb API and its available endpoints, please refer to the official documentation.

Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

License
This project is licensed under the MIT License.
