# MealDB App

A modern Android app built with Jetpack Compose to display meal categories from the MealDB API. This app showcases dynamic color themes, beautiful UI components, and efficient data handling using `ViewModel` and `LiveData`. The app also implements a loading indicator during data fetch operations. It also utilizes **Hilt** for Dependency Injection (DI) to manage app dependencies.

## Features

- Displays a list of meal categories fetched from the MealDB API.
- Uses Jetpack Compose for UI rendering, with modern, dynamic designs.
- Handles asynchronous data loading with a circular progress indicator.
- Utilizes **ViewModel**, **LiveData**, and **Coroutine** for effective data management and network calls.
- **Hilt** for **Dependency Injection** to manage the app's dependencies and provide a scalable and testable architecture.
- Navigation between screens using the **NavController** and **NavHost**.
- Navigate to a detailed view for each meal category.

## Screenshots

### Home Screen (Meal Categories)
![Home Screen](https://teluguquran.in/images/one.jpeg)

### Category Details Screen
![Category Details](https://teluguquran.in/images/two.jpeg)

## App Design

- The app uses a **LazyColumn** to display meal categories in a scrollable list.
- Each category is shown in a card with an image, title, and description.
- Clicking on a category navigates to a detail screen showing more information about the selected meal category.
- A **CircularProgressIndicator** is shown while the data is being loaded.
 
