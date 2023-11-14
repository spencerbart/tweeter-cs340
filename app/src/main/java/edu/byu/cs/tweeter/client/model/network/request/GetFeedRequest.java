package edu.byu.cs.tweeter.client.model.network.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;

public class GetFeedRequest {
    private AuthToken authToken;
    private String userAlias;
    private int limit;
    private long lastStatusTimestamp;

    public GetFeedRequest(AuthToken authToken, String userAlias, int limit, long lastStatusTimestamp) {
        this.authToken = authToken;
        this.userAlias = userAlias;
        this.limit = limit;
        this.lastStatusTimestamp = lastStatusTimestamp;
    }
}
