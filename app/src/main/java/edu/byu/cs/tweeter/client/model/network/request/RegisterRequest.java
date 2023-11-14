package edu.byu.cs.tweeter.client.model.network.request;

public class RegisterRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String image;
    private String password;

    public RegisterRequest(String username, String firstName, String lastName, String image, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.password = password;
    }
}
