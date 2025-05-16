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

https://github.com/user-attachments/assets/987039d6-abab-4d7a-8d3e-228c83fcb748



**Scenario 2**: List of random images of a particular breed


https://github.com/user-attachments/assets/85c1d0d8-08ce-4567-883a-295b22f899cf



**Scenario 3**: List of bread - error scenario 


https://github.com/user-attachments/assets/349c4c87-56e8-4bca-ad96-f76be5dab7ec



**Scenario 4**: List of random images  - error scenario




https://github.com/user-attachments/assets/4555e503-62d7-4f77-bc80-670526189543






