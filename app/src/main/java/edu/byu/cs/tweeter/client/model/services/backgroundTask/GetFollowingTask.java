package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Handler;

import java.util.List;

import edu.byu.cs.tweeter.client.model.network.request.GetFollowingRequest;
import edu.byu.cs.tweeter.client.model.network.response.GetFollowingResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.Pair;

public class GetFollowingTask extends PagedUserTask {


    public GetFollowingTask(AuthToken authToken, User targetUser, int limit, User lastFollowee,
                            Handler messageHandler) {
        super(authToken, targetUser, limit, lastFollowee, messageHandler);
    }

    @Override
    protected Pair<List<User>, Boolean> getItems() {
//        return getFakeData().getPageOfUsers(getLastItem(), getLimit(), getTargetUser());
        try {
            GetFollowingRequest request = new GetFollowingRequest(getAuthToken(), getTargetUser().getAlias(),
                    getLimit(), getLastItem() == null ? null : getLastItem().getAlias());
            GetFollowingResponse response = getServerFacade().getFollowing(request, "/following");

            if (response.isSuccess()) {
                return new Pair<>(response.getFollowees(), response.getHasMorePages());
            } else {
                throw new Exception(response.getMessage());
            }

        } catch (Exception e) {
            sendExceptionMessage(e);
            return new Pair<>(null, false);
        }
    }

}