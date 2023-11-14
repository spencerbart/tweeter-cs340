package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Handler;

import edu.byu.cs.tweeter.client.model.network.request.GetFollowingCountRequest;
import edu.byu.cs.tweeter.client.model.network.response.GetFollowingCountResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class GetFollowingCountTask extends GetCountTask {

    public GetFollowingCountTask(AuthToken authToken, User targetUser, Handler messageHandler) {
        super(authToken, targetUser, messageHandler);
    }

    @Override
    protected int runCountTask() {
//        return 20;
        try {
            GetFollowingCountRequest request = new GetFollowingCountRequest(getAuthToken(), getTargetUser());
            GetFollowingCountResponse response = getServerFacade().getFollowingCount(request, "/following-count");
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