package edu.byu.cs.tweeter.client.model.network.response;

public class GetFollowingCountResponse extends Response {
    private int count;

    public GetFollowingCountResponse(boolean success) {
        super(success);
    }

    public int getCount() {
        return count;
    }

}
