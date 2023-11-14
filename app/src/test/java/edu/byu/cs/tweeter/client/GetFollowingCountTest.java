package edu.byu.cs.tweeter.client;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.network.ServerFacade;
import edu.byu.cs.tweeter.client.model.network.TweeterRemoteException;
import edu.byu.cs.tweeter.client.model.network.request.GetFollowingCountRequest;
import edu.byu.cs.tweeter.client.model.network.response.GetFollowingCountResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class GetFollowingCountTest {
    @Test
    public void testGetFollowingCount() throws IOException, TweeterRemoteException {
        User user = new User("test", "test", "@test", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        GetFollowingCountRequest request = new GetFollowingCountRequest(new AuthToken("abc"), user);
        ServerFacade serverFacade = new ServerFacade();
        GetFollowingCountResponse response = serverFacade.getFollowingCount(request, "/following-count");

        assert(response.isSuccess());
        assert(response.getCount() == 20);
    }
}
