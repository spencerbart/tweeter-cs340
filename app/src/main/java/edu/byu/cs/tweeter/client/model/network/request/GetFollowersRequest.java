package edu.byu.cs.tweeter.client.model.network.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;

public class GetFollowersRequest {
    private AuthToken authToken;
    private String followeeAlias;
    private int limit;
    private String lastFollowerAlias;

    public GetFollowersRequest(AuthToken authToken, String followeeAlias, int limit, String lastFollowerAlias) {
        this.authToken = authToken;
        this.followeeAlias = followeeAlias;
        this.limit = limit;
        this.lastFollowerAlias = lastFollowerAlias;
    }

//    public AuthToken getAuthToken() { return authToken; }
}
