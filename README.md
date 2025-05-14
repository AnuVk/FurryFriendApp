# Dog breed list App
This app uses Dog API to display a list of breed which is grouped alphabetically. Clicking on any of the breed shows a list of 10 randomimages for the breed selected

https://dog.ceo/dog-api/documentation/
https://github.com/ElliottLandsborough/dog-ceo-api

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
Scenario 1: Show list of breeds and clicking on any breed, show random 10 images of the breed


https://github.com/user-attachments/assets/1e82a989-4a3f-4de0-a924-a6c7533a5aab


https://github.com/user-attachments/assets/b9b34233-5684-45d7-80d3-757c216bf526




Scenario 2: Show error screen when the list of breeds endpoint fails



https://github.com/user-attachments/assets/5450e47a-e8ff-4e32-ac9f-83a7be8c5517



Scenario 3 Show error screen when the list of breed images endpoint fails



https://github.com/user-attachments/assets/503bc802-460b-4044-8ad5-20c7a42840bc



