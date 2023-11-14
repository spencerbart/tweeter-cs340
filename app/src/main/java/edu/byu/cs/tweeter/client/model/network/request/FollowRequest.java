package edu.byu.cs.tweeter.client.model.network.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;

public class FollowRequest {
    private AuthToken authToken;
    private String followeeAlias;

    public FollowRequest(AuthToken authToken, String followeeAlias) {
        this.authToken = authToken;
        this.followeeAlias = followeeAlias;
    }

    public String getFolloweeAlias() { return followeeAlias; }
}
