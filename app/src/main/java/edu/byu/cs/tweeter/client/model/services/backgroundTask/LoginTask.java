package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Handler;

import edu.byu.cs.tweeter.client.model.network.request.LoginRequest;
import edu.byu.cs.tweeter.client.model.network.response.LoginResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.Pair;

public class LoginTask extends AuthenticateTask {

    public LoginTask(String username, String password, Handler messageHandler) {
        super(username, password, messageHandler);
    }

    @Override
    protected Pair<User, AuthToken> runAuthenticationTask() {
//        User loggedInUser = getFakeData().getFirstUser();
//        AuthToken authToken = getFakeData().getAuthToken();
//        return new Pair<>(loggedInUser, authToken);
        try {
            LoginRequest request = new LoginRequest(getUsername(), getPassword());
            LoginResponse response = getServerFacade().login(request, "/login");
            if (response.isSuccess()) {
                return new Pair<>(response.getUser(), response.getAuthToken());
            } else {
                throw new Exception(response.getMessage());
            }
        } catch (Exception e) {
            sendExceptionMessage(e);
            return new Pair<>(null, null);
        }
    }
}