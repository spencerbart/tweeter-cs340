package edu.byu.cs.tweeter.client.model.network.response;

public class IsFollowerResponse extends Response {
    private boolean isFollower;

    public IsFollowerResponse(boolean success) {
        super(success);
    }

    public boolean isFollower() {
        return isFollower;
    }
}
