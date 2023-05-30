
# Libraries Used
- Hilt Dependency injection to provide constructor dependency injection to classes in the application
- Retrofit to provide access to the backend API endpoints
- Coroutines to run API network requests on background threads
- AndroidX to provide Lifecycle and LiveData functionality to the app
- View binding to bind the inflated layout files to instances running in the application code.


# Architecture Design
![Alt text](app/docs/images/mvvm_architecture.png?raw=true "MVVM Architecture") 

The main component in the MVVM design pattern are :

View - Informs the ViewModel about the user’s actions
ViewModel - Exposes streams of data relevant to the View
DataModel - Abstracts the data source. The ViewModel works with the DataModel to get and save the data.
