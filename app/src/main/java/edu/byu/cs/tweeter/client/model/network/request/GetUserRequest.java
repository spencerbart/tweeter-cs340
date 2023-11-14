package edu.byu.cs.tweeter.client.model.network.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;

public class GetUserRequest {
    private AuthToken authToken;
    private String alias;

    public GetUserRequest(AuthToken authToken, String alias) {
        this.authToken = authToken;
        this.alias = alias;
    }
}
