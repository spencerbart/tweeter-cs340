package edu.byu.cs.tweeter.client;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.network.ServerFacade;
import edu.byu.cs.tweeter.client.model.network.TweeterRemoteException;
import edu.byu.cs.tweeter.client.model.network.request.GetFollowersRequest;
import edu.byu.cs.tweeter.client.model.network.response.GetFollowersResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;

public class GetFollowersTest {
    @Test
    public void testGetFollowers() throws IOException, TweeterRemoteException {
        GetFollowersRequest request = new GetFollowersRequest(new AuthToken("abc"), "@test",10, "@test");
        ServerFacade serverFacade = new ServerFacade();
        GetFollowersResponse response = serverFacade.getFollowers(request, "/followers");

        assert(response.isSuccess());
        assert(response.getFollowers() != null);
        assert(response.getFollowers().size() == 10);
    }
}
