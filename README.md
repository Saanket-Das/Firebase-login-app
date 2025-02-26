Firebase Login App

This is a simple Firebase Authentication app built using Android Studio (Java & XML). It allows users to register, login, and logout using Firebase Authentication.

Features

User authentication using Firebase Authentication

Login with email and password

Register new users

Logout functionality

Displays logged-in user’s email

Technologies Used

Android Studio (Java & XML)

Firebase Authentication

Installation & Setup

Download it

Open in Android Studio

Open Android Studio and select "Open an existing project."

Navigate to the cloned project directory and open it.

Setup Firebase

Create a Firebase project at Firebase Console.

Enable Email/Password Authentication under Authentication.

Download the google-services.json file and place it in app/ directory.

Run the App

Connect an Android device or use an emulator.

Click Run in Android Studio.

Code Structure

MainActivity.java

Handles user authentication:

Login using Firebase Authentication

Logout by calling mAuth.signOut()

Redirects to RegisterActivity for new users

Screenshots

Add screenshots of your app interface here.

License

This project is licensed under the MIT License.

