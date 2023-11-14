package edu.byu.cs.tweeter.client.model.network;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.network.request.FollowRequest;
import edu.byu.cs.tweeter.client.model.network.request.GetFeedRequest;
import edu.byu.cs.tweeter.client.model.network.request.GetFollowersCountRequest;
import edu.byu.cs.tweeter.client.model.network.request.GetFollowersRequest;
import edu.byu.cs.tweeter.client.model.network.request.GetFollowingCountRequest;
import edu.byu.cs.tweeter.client.model.network.request.GetFollowingRequest;
import edu.byu.cs.tweeter.client.model.network.request.GetStoryRequest;
import edu.byu.cs.tweeter.client.model.network.request.GetUserRequest;
import edu.byu.cs.tweeter.client.model.network.request.IsFollowerRequest;
import edu.byu.cs.tweeter.client.model.network.request.LoginRequest;
import edu.byu.cs.tweeter.client.model.network.request.LogoutRequest;
import edu.byu.cs.tweeter.client.model.network.request.PostStatusRequest;
import edu.byu.cs.tweeter.client.model.network.request.UnfollowRequest;
import edu.byu.cs.tweeter.client.model.network.response.FollowResponse;
import edu.byu.cs.tweeter.client.model.network.response.GetFeedResponse;
import edu.byu.cs.tweeter.client.model.network.response.GetFollowersCountResponse;
import edu.byu.cs.tweeter.client.model.network.response.GetFollowersResponse;
import edu.byu.cs.tweeter.client.model.network.response.GetFollowingCountResponse;
import edu.byu.cs.tweeter.client.model.network.response.GetFollowingResponse;
import edu.byu.cs.tweeter.client.model.network.response.GetStoryResponse;
import edu.byu.cs.tweeter.client.model.network.response.GetUserResponse;
import edu.byu.cs.tweeter.client.model.network.response.IsFollowerResponse;
import edu.byu.cs.tweeter.client.model.network.response.LoginResponse;
import edu.byu.cs.tweeter.client.model.network.response.LogoutResponse;
import edu.byu.cs.tweeter.client.model.network.response.PostStatusResponse;
import edu.byu.cs.tweeter.client.model.network.response.UnfollowResponse;

public class ServerFacade {

    // TODO: Set this to the invoke URL of your API. Find it by going to your API in AWS, clicking
    //  on stages in the right-side menu, and clicking on the stage you deployed your API to.
    private static final String SERVER_URL = "https://4lfyijqjfjleofyyo3v7gn57xi0nykvv.lambda-url.us-west-2.on.aws";
//    private static final String SERVER_URL = "http://0.0.0.0:9000";

    private final ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);

    public FollowResponse follow(FollowRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, FollowResponse.class);
    }

    public UnfollowResponse unfollow(UnfollowRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, UnfollowResponse.class);
    }

    public GetFollowersResponse getFollowers(GetFollowersRequest request, String urlPath)
            throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, GetFollowersResponse.class);
    }

    public GetFollowersCountResponse getFollowersCount(GetFollowersCountRequest request, String urlPath)
            throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, GetFollowersCountResponse.class);
    }

    public GetFollowingResponse getFollowing(GetFollowingRequest request, String urlPath)
            throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, GetFollowingResponse.class);
    }

    public GetFollowingCountResponse getFollowingCount(GetFollowingCountRequest request, String urlPath)
            throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, GetFollowingCountResponse.class);
    }

    public IsFollowerResponse isFollower(IsFollowerRequest request, String urlPath)
            throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, IsFollowerResponse.class);
    }

    public PostStatusResponse postStatus(PostStatusRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, PostStatusResponse.class);
    }

    public GetFeedResponse getFeed(GetFeedRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, GetFeedResponse.class);
    }

    public GetStoryResponse getStory(GetStoryRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, GetStoryResponse.class);
    }

    public LoginResponse login(LoginRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);
    }

//    public RegisterResponse register(RegisterRequest request, String urlPath) throws IOException, TweeterRemoteException {
//        return clientCommunicator.doPost(urlPath, request, null, RegisterResponse.class);
//    }

    public LogoutResponse logout(LogoutRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, LogoutResponse.class);
    }

    public GetUserResponse getUser(GetUserRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, GetUserResponse.class);
    }

}
