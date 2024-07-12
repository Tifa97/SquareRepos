## SquareRepos


SquareRepos is an Android application that allows users to view github repositories. It follows the MVVM architecture pattern for structured and maintainable code.

### Architecture


The app follows the MVVM architecture pattern for structured and maintainable code. MVVM was chosen for SquareRepos due to its separation of UI from business logic which improves code readability maintainability and testability. MVVM was preferred over MVI for it's simpler design, which reduces complexity and overhead in managing application state and user interactions, leading to a more straightforward development process.

### UI

App contains 2 screens. ReposOverviewScreen and RepoDetailsScreen:
    
1.  **ReposOverviewScreen**: Fetches and displays repos. It displays avatar image of the owner, repo name and a description. Every repo can be clicked. Once clicked, RepoDetailsScreen opens
    
2.  **RepoDetailsScreen**: Fetches and displays details for the selected repo. Contains repo owner avatar image on top with details contained in a column under the image. If needed in the future, column can be made scrollable which would allow more data to be shown.
    

### Error Handling

**Error while fetching repos/Error fetching repo with name**  - getting an error like this on the screen means that there was something wrong while fetching (no connection, server issues...)
    

### Packages
   
1.  **di**: Contains the dependency injection setup inside Modules.kt file with the Koin module for providing dependencies throughout the app.
    
2.  **navigation**: Manages navigation within the app, including the NavHost setup and screen routes.
    
3.  **remote**: Handles remote data fetching using Retrofit, including the definition of API endpoints and response data classes.
    
4.  **repository**: Contains BackendRepository for handling calls to the API and mapping the response.
    
6.  **ui**: Contains Jetpack Compose theming and UI components, including various composables for different screens within the app.
    
7.  **util**: Contains utility classes and functions used throughout the app.
    
8.  **viewmodel**: Contains ViewModel classes for each screen in the app, responsible for managing UI-related data and communication with the repository layer.
    

### Dependencies

The app uses various dependencies, including:

-   Jetpack Compose for building the UI. Jetpack Compose was chosen rather than the classic approach with xml layouts since this is the latest technology for Android. It speeds up the development by making code shorter which is most obvious when using LazyColumn/LazyRow instead of RecyclerView.
-   Retrofit for making network requests.
-   Koin for dependency injection. Koin was chosen over Dagger Hilt for its simplicity and ease of use, enabling efficient dependency injection throughout the app without the need for complex setup and annotations
-   Android Navigation Component for managing navigation within the app.
-  Coil for handling load and display of images
-  Mockito, MockWebServer and Robolectric for unit testing.