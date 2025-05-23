// package controller;

// import model.User;
// import view.RegisterView;
// import view.LoginView;

// public class UserController {
//     private RegisterView registerView;
//     private User model;

//     public UserController(RegisterView registerView, User model) {
//         this.registerView = registerView;
//         this.model = model;
//     }

//     // Handle user registration
//     public void registerUser() {
//         System.out.println("User registration initiated");

//         String username = registerView.getUsername();
//         String password = registerView.getPassword();
//         String email = registerView.getEmail();

//         // Set the model fields
//         model.setUsername(username);
//         model.setPassword(password); // In a real application, don't store plain text passwords
//         model.setEmail(email);

//         // Register the user in the database
//         boolean isRegistered = model.register();

//         // Provide feedback to the user
//         if (isRegistered) {
//             registerView.showMessage("Registration successful!");

//             // After successful registration, show the LoginView and hide the RegisterView
//             LoginView loginView = new LoginView();
//             loginView.show();
//             registerView.hide(); // Hide the register view
//         } else {
//             registerView.showMessage("Registration failed. Try again.");
//         }
//     }
// }
package controller;

import model.User;
import view.RegisterView;
import view.LoginView;

public class UserController {
    private RegisterView registerView;
    private User model;

    public UserController(RegisterView registerView, User model) {
        this.registerView = registerView;
        this.model = model;
    }

    // Handle user registration
    public void registerUser() {
        System.out.println("User registration initiated");

        String username = registerView.getUsername();
        String password = registerView.getPassword();
        String email = registerView.getEmail();

        // Set the model fields
        model.setUsername(username);
        model.setPassword(password); // Reminder: In production, hash passwords!
        model.setEmail(email);

        // Register the user in the database
        boolean isRegistered = model.register();

        // Provide feedback to the user
        if (isRegistered) {
            registerView.showMessage("Registration successful!");

            // Switch to login view
            LoginView loginView = new LoginView();
            loginView.show();
            registerView.hide();
        } else {
            registerView.showMessage("Registration failed. Try again.");
        }
    }
}
