package edu.byu.cs.tweeter.client.model.network.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class IsFollowerRequest {
    private AuthToken authToken;
    private User follower;
    private User followee;

    public IsFollowerRequest(AuthToken authToken, User follower, User followee) {
        this.authToken = authToken;
        this.follower = follower;
        this.followee = followee;
    }
}
