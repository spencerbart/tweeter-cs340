package edu.byu.cs.tweeter.client.model.network.response;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;

public class GetFollowingResponse extends PagedResponse {
    private List<User> followees;

    public GetFollowingResponse(String message) {
        super(false, message, false);
    }

    public GetFollowingResponse(List<User> followees, boolean hasMorePages) {
        super(true, hasMorePages);
        this.followees = followees;
    }

    public List<User> getFollowees() {
        return followees;
    }
}
