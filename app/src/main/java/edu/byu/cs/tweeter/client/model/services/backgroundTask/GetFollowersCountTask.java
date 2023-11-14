package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Handler;

import edu.byu.cs.tweeter.client.model.network.request.GetFollowersCountRequest;
import edu.byu.cs.tweeter.client.model.network.response.GetFollowersCountResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class GetFollowersCountTask extends GetCountTask {

    public GetFollowersCountTask(AuthToken authToken, User targetUser, Handler messageHandler) {
        super(authToken, targetUser, messageHandler);
    }

    @Override
    protected int runCountTask() {
        try {
            GetFollowersCountRequest request = new GetFollowersCountRequest(getAuthToken(), getTargetUser());
            GetFollowersCountResponse response = getServerFacade().getFollowersCount(request, "/followers-count");
            if (response.isSuccess()) {
                return response.getCount();
            } else {
                sendFailedMessage(response.getMessage());
            }
        } catch (Exception e) {
            sendExceptionMessage(e);
        }
        return 0;
    }
}