package model;

// Plain text strategy (not secure, just for example)
public class PlainTextAuthenticationStrategy implements AuthenticationStrategy {
    @Override
    public boolean authenticate(String inputPassword, String storedPassword) {
        return inputPassword.equals(storedPassword);
    }
}
