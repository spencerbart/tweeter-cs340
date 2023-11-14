package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import edu.byu.cs.tweeter.client.model.network.request.FollowRequest;
import edu.byu.cs.tweeter.client.model.network.response.FollowResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

/**
 * Background task that establishes a following relationship between two users.
 */
public class FollowTask extends AuthenticatedTask {
    /**
     * The user that is being followed.
     */
    private final User followee;

    public FollowTask(AuthToken authToken, User followee, Handler messageHandler) {
        super(authToken, messageHandler);
        this.followee = followee;
    }

    @Override
    protected void runTask() {
        try {
            FollowRequest request = new FollowRequest(getAuthToken(), followee.getAlias());
            FollowResponse response = getServerFacade().follow(request, "/follow");
            if (response.isSuccess()) {
                sendSuccessMessage();
            } else {
//                throw new Exception(response.getMessage());
                sendFailedMessage(response.getMessage());
            }
        } catch (Exception ex) {
            Log.e("FollowTask", ex.getMessage(), ex);
            sendExceptionMessage(ex);
        }
    }
}
