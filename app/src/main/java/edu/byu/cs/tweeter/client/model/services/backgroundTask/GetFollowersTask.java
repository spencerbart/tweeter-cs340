package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Handler;

import java.util.List;

import edu.byu.cs.tweeter.client.model.network.request.GetFollowersRequest;
import edu.byu.cs.tweeter.client.model.network.response.GetFollowersResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.Pair;

public class GetFollowersTask extends PagedUserTask {

    public GetFollowersTask(AuthToken authToken, User targetUser, int limit, User lastFollower,
                            Handler messageHandler) {
        super(authToken, targetUser, limit, lastFollower, messageHandler);
    }

    @Override
    protected Pair<List<User>, Boolean> getItems() {
        try {
            GetFollowersRequest request = new GetFollowersRequest(getAuthToken(), getTargetUser().getAlias(),
                    getLimit(), getLastItem() == null ? null : getLastItem().getAlias());
            GetFollowersResponse response = getServerFacade().getFollowers(request, "/followers");

            if (response.isSuccess()) {
                return new Pair<>(response.getFollowers(), response.getHasMorePages());
            } else {
                throw new Exception(response.getMessage());
            }
        } catch (Exception e) {
            sendExceptionMessage(e);
            return new Pair<>(null, false);
        }
    }
}