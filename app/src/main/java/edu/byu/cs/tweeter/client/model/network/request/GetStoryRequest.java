package edu.byu.cs.tweeter.client.model.network.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class GetStoryRequest {
    private AuthToken authToken;
    private User targetUser;
    private int limit;
    private Status lastStatus;

    public GetStoryRequest(AuthToken authToken, User targetUser, int limit, Status lastStatus) {
        this.authToken = authToken;
        this.targetUser = targetUser;
        this.limit = limit;
        this.lastStatus = lastStatus;
    }
}
