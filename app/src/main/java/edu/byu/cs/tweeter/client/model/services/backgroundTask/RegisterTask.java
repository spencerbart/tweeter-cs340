package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Handler;

import edu.byu.cs.tweeter.client.model.network.request.RegisterRequest;
import edu.byu.cs.tweeter.client.model.network.response.RegisterResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.Pair;

public class RegisterTask extends AuthenticateTask {

    /**
     * The user's first name.
     */
    private final String firstName;

    /**
     * The user's last name.
     */
    private final String lastName;

    /**
     * The base-64 encoded bytes of the user's profile image.
     */
    private final String image;

    public RegisterTask(String firstName, String lastName, String username, String password,
                        String image, Handler messageHandler) {
        super(username, password, messageHandler);
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }

    @Override
    protected Pair<User, AuthToken> runAuthenticationTask() {
//        User registeredUser = getFakeData().getFirstUser();
//        AuthToken authToken = getFakeData().getAuthToken();
//        return new Pair<>(registeredUser, authToken);
        try {
            RegisterRequest request = new RegisterRequest(getUsername(), firstName, lastName, image, getPassword());
            RegisterResponse response = getServerFacade().register(request, "/register");
            if (response.isSuccess()) {
                return new Pair<>(response.getUser(), response.getAuthToken());
            } else {
                throw new Exception(response.getMessage());
            }
        } catch (Exception ex) {
            sendExceptionMessage(ex);
            return null;
        }
    }
}