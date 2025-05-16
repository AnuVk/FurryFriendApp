# Dog breed list App
This app uses Dog API to display a list of breed which is grouped alphabetically. Clicking on any of the breed shows a list of 10 randomimages for the breed selected

https://dog.ceo/dog-api/documentation/
https://github.com/ElliottLandsborough/dog-ceo-api

### Project Setup

Since I have used AGP 8.9, the Android studdio version must be compatible for this to work. I have used Android Studio Meerkat | 2024.3.1 Patch 2
You can refer this compatibility matrix

https://developer.android.com/build/releases/gradle-plugin

<img width="941" alt="Screenshot 2025-05-15 at 00 14 49" src="https://github.com/user-attachments/assets/34d54cd6-39f8-403a-a417-d15187e16012" />

### Architecture
The app follows the MVVM architecture, utilizing coroutines and Stateflow for efficient asynchronous operations and state management. While clean architecture principles are applied, the layers are not separated into distinct 
modules due to the project's small scale. 
In a larger project, modularization with clear dependencies would be ideal.

### Technical decisions
**Clean architecture**:  Chosen for its separation of concerns and testability, facilitating unit testing of individual layers.

**Libraries**: I used coroutines and Stateflow, which I have used in my current project along with Hilt for dependency injection, and Retrofit for network requests.
I have used Jetpack Compose as part of populating the views to overcome the overhead of xml. I have applied some of my learning in this project by using the NavHost for creating a navigation destination.
Also used Coil for displaying the images in 2nd screen as it works well with Jetpack compose.

**UI with Jetpack compose**: 
I wanted to create a list with image and breedname where image would be a random image of a particular breed, but i couldnt find any endpoint that catered to my needs.
I did go down the route of chaining 2 calls to achieve this but it would be very expensive. Hence the decision I took was to display a simple list and group them alphabetically using Sticky header

**Viewmodel**: 
Upon successfully retrieving the dog breed list, the ViewModel checks the status and emit either success or Error. This state is being listened in the composable in order to show the relevant state


**Testing**: 
The project incorporates tests at the repository and ViewModel levels using Turbine for flow testing and MockK for mocking dependencies.

### Video:

**Scenario 1** : List of breeds


https://github.com/user-attachments/assets/53e614c7-0445-4142-8e30-be9c4be34bf1



**Scenario 2**: List of random images of a particular breed

https://github.com/user-attachments/assets/3e44a5e6-fbdd-427d-9c43-63bfea3979d0


**Scenario 3**: Show error screen when the list of breeds endpoint fails


https://github.com/user-attachments/assets/2ee1daee-e69e-499e-89da-4e2ad349f302



**Scenario 4** Show error screen when the list of breed images endpoint fails


https://github.com/user-attachments/assets/748f7b49-a2e0-4ecb-9dd5-e36606fb337b



Scenario 4 Show error screen when the list of breed images endpoint fails




