package edu.byu.cs.tweeter.client.model.network.response;

public class GetFollowersCountResponse extends Response {
    private int count;

    public GetFollowersCountResponse(boolean success) {
        super(success);
    }

    public int getCount() {
        return count;
    }
}
