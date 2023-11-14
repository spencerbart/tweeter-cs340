package edu.byu.cs.tweeter.client.model.network.response;

import edu.byu.cs.tweeter.model.domain.User;

public class GetUserResponse extends Response {
    private User user;

    public GetUserResponse(boolean success) {
        super(success);
    }

    public User getUser() {
        return user;
    }
}
