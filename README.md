<!-- ABOUT THE PROJECT -->
## DeveloperApplication_MVVM
<p float="left">
  <img src="https://user-images.githubusercontent.com/18207490/161450510-1cf8e78d-b393-40cc-b563-318cca5be571.png" height="300">
  <img src="https://user-images.githubusercontent.com/18207490/161450511-444149ab-daba-4221-a2d6-29cd6aa485e6.png" width="300">
  <img src="https://user-images.githubusercontent.com/18207490/161450512-3fd74ac7-52f9-4b36-a4f7-d89f0906c0fc.png" height="300">
  <img src="https://user-images.githubusercontent.com/18207490/161450513-e0c9953c-a10d-4eda-8740-5d919a6472d7.png" height="300">
  <img src="https://user-images.githubusercontent.com/18207490/161450514-0e788dcf-ecb2-40d4-942e-7597c17d2fa2.png" height="300>
  <img src="https://user-images.githubusercontent.com/18207490/161450515-34907ad4-5940-43a7-a774-d9f9b147a8b5.png" height="300><br>
</p>

  <img src="https://user-images.githubusercontent.com/18207490/161450781-57e71d35-c008-4b23-a44b-3b3b691fde3c.png" height="300/>
<br>

<p float="left">
This application is written using the https://s3-eu-west-1.amazonaws.com/developer-application-test/ API. The application, MVVM, Coroutines, Hilt, Navigation component features were used.
</p>
 * Architecture is written in MVVM. 
 * Asynchronous transactions were made with Coroutines. 
 * StateFlow was used to control the values returned in the Retrofit and to perform operations according to the returned values.
 * Picasso is used to display the pictures.
 * Hilt is used for Dependency Injection.

### Built With

Libraries used in the application.

* [Coroutines](https://developer.android.com/kotlin/coroutines)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
* [View Binding](https://developer.android.com/topic/libraries/view-binding)
* [Room Database](https://developer.android.com/training/data-storage/room)
* [Retrofit](https://square.github.io/retrofit/)
* [Picasso](https://square.github.io/picasso/)

<!-- GETTING STARTED -->
## Getting Started

### Installation

1. Get a free API
2. Enter your API in `build.gradle`
   ```js
   buildConfigField 'String', 'API_BASE_URL', '"https://s3-eu-west-1.amazonaws.com/developer-application-test/"'
