package edu.byu.cs.tweeter.client.model.network.response;

public class FollowResponse extends Response {
    public FollowResponse(boolean success) {
        super(success);
    }

    public FollowResponse(boolean success, String message) {
        super(success, message);
    }

}
