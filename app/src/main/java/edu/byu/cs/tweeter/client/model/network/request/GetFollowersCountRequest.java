package edu.byu.cs.tweeter.client.model.network.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class GetFollowersCountRequest {
    private AuthToken authToken;
    private User targetUser;

    public GetFollowersCountRequest(AuthToken authToken, User targetUser) {
        this.authToken = authToken;
        this.targetUser = targetUser;
    }
}
