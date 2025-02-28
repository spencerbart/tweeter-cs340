package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Handler;

import edu.byu.cs.tweeter.client.model.network.request.LogoutRequest;
import edu.byu.cs.tweeter.client.model.network.response.LogoutResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;

public class LogoutTask extends AuthenticatedTask {

    public LogoutTask(AuthToken authToken, Handler messageHandler) {
        super(authToken, messageHandler);
    }

    @Override
    protected void runTask() {
        // We could do this from the presenter, without a task and handler, but we will
        // eventually remove the auth token from  the DB and will need this then.

        // Call sendSuccessMessage if successful
//        sendSuccessMessage();
        // or call sendFailedMessage if not successful
        // sendFailedMessage()

        try {
            LogoutRequest request = new LogoutRequest(getAuthToken());
            LogoutResponse response = getServerFacade().logout(request, "/logout");
            if (response.isSuccess()) {
                sendSuccessMessage();
            } else {
                sendFailedMessage(response.getMessage());
            }
        } catch (Exception ex) {
            sendExceptionMessage(ex);
        }
    }
}