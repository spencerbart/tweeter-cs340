package edu.byu.cs.tweeter.client.model.network;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.network.request.GetFeedRequest;
import edu.byu.cs.tweeter.client.model.network.request.GetFollowingRequest;
import edu.byu.cs.tweeter.client.model.network.request.LoginRequest;
import edu.byu.cs.tweeter.client.model.network.response.GetFeedResponse;
import edu.byu.cs.tweeter.client.model.network.response.GetFollowingResponse;
import edu.byu.cs.tweeter.client.model.network.response.LoginResponse;

public class ServerFacade {

    // TODO: Set this to the invoke URL of your API. Find it by going to your API in AWS, clicking
    //  on stages in the right-side menu, and clicking on the stage you deployed your API to.
    private static final String SERVER_URL = "https://4lfyijqjfjleofyyo3v7gn57xi0nykvv.lambda-url.us-west-2.on.aws";
//    private static final String SERVER_URL = "http://0.0.0.0:9000";

    private final ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);

    /**
     * Performs a login and if successful, returns the logged in user and an auth token.
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public LoginResponse login(LoginRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains information about the user whose followees are to be returned and any
     *                other information required to satisfy the request.
     * @return the followees.
     */
    public GetFollowingResponse getFollowing(GetFollowingRequest request, String urlPath)
            throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, GetFollowingResponse.class);
    }

    public GetFeedResponse getFeed(GetFeedRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, GetFeedResponse.class);
    }
}
