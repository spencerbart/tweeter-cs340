package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Bundle;
import android.os.Handler;

import edu.byu.cs.tweeter.client.model.network.request.GetUserRequest;
import edu.byu.cs.tweeter.client.model.network.response.GetUserResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class GetUserTask extends AuthenticatedTask {

    public static final String USER_KEY = "user";

    /**
     * Alias (or handle) for user whose profile is being retrieved.
     */
    private final String alias;

    private User user;

    public GetUserTask(AuthToken authToken, String alias, Handler messageHandler) {
        super(authToken, messageHandler);
        this.alias = alias;
    }

    @Override
    protected void runTask() {
//        user = getUser();
        try {
            GetUserRequest request = new GetUserRequest(getAuthToken(), alias);
            GetUserResponse response = getServerFacade().getUser(request, "/user");
            if (response.isSuccess()) {
                user = response.getUser();
            } else {
                throw new Exception(response.getMessage());
            }
        } catch (Exception ex) {
            sendExceptionMessage(ex);
        }

        // Call sendSuccessMessage if successful
        sendSuccessMessage();
        // or call sendFailedMessage if not successful
        // sendFailedMessage()
    }

    @Override
    protected void loadSuccessBundle(Bundle msgBundle) {
        msgBundle.putSerializable(USER_KEY, user);
    }

    private User getUser() {
        return getFakeData().findUserByAlias(alias);
    }
}