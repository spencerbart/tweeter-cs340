package edu.byu.cs.tweeter.client.model.network.response;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;

public class GetFollowersResponse extends PagedResponse {
    private List<User> followers;

    public GetFollowersResponse(String message) {
        super(false, message, false);
    }

    public List<User> getFollowers() {
        return followers;
    }
}
