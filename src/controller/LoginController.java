// package controller;

// import model.User;
// import view.LoginView;

// public class LoginController {
//     private LoginView loginView;
//     private User model;

//     public LoginController(LoginView loginView, User model) {
//         this.loginView = loginView;
//         this.model = model;

//         // Set button action
//         this.loginView.setLoginAction(e -> loginUser());
//     }

//     private void loginUser() {
//         String username = loginView.getUsername();
//         String password = loginView.getPassword();

//         boolean isValid = model.login(username, password);

//         if (isValid) {
//             loginView.showMessage("Login successful!");
//             // TODO: redirect to dashboard or next page
//         } else {
//             loginView.showMessage("Invalid credentials. Please try again.");
//         }
//     }
// }

