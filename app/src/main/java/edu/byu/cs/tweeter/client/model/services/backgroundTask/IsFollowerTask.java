package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Bundle;
import android.os.Handler;

import java.util.Random;

import edu.byu.cs.tweeter.client.model.network.request.IsFollowerRequest;
import edu.byu.cs.tweeter.client.model.network.response.IsFollowerResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class IsFollowerTask extends AuthenticatedTask {

    public static final String IS_FOLLOWER_KEY = "is-follower";

    /**
     * The alleged follower.
     */
    private final User follower;

    /**
     * The alleged followee.
     */
    private final User followee;

    private boolean isFollower;

    public IsFollowerTask(AuthToken authToken, User follower, User followee, Handler messageHandler) {
        super(authToken, messageHandler);
        this.follower = follower;
        this.followee = followee;
    }

    @Override
    protected void runTask() {
//        isFollower = new Random().nextInt() > 0;
        try {
            IsFollowerRequest request = new IsFollowerRequest(getAuthToken(), follower, followee);
            IsFollowerResponse response = getServerFacade().isFollower(request, "/is-follower");
            if (response.isSuccess()) {
                this.isFollower = response.isFollower();
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
        msgBundle.putBoolean(IS_FOLLOWER_KEY, isFollower);
    }
}