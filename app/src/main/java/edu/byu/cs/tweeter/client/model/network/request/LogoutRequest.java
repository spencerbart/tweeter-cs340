package edu.byu.cs.tweeter.client.model.network.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;

public class LogoutRequest {
    private AuthToken authToken;

    public LogoutRequest(AuthToken authToken) {
        this.authToken = authToken;
    }
}
