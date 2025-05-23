package model;

public interface AuthenticationStrategy {
    boolean authenticate(String inputPassword, String storedPassword);
}
