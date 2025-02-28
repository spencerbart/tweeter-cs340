package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Handler;

import edu.byu.cs.tweeter.client.model.network.request.UnfollowRequest;
import edu.byu.cs.tweeter.client.model.network.response.UnfollowResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class UnfollowTask extends AuthenticatedTask {

    /**
     * The user that is being followed.
     */
    private final User followee;

    public UnfollowTask(AuthToken authToken, User followee, Handler messageHandler) {
        super(authToken, messageHandler);
        this.followee = followee;
    }

    @Override
    protected void runTask() {
        // We could do this from the presenter, without a task and handler, but we will
        // eventually access the database from here when we aren't using dummy data.

        // Call sendSuccessMessage if successful
//        sendSuccessMessage();
        // or call sendFailedMessage if not successful
        // sendFailedMessage()
        try {
            UnfollowRequest request = new UnfollowRequest(getAuthToken(), followee.getAlias());
            UnfollowResponse response = getServerFacade().unfollow(request, "/unfollow");
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