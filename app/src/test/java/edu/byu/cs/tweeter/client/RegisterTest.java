package edu.byu.cs.tweeter.client;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.network.ServerFacade;
import edu.byu.cs.tweeter.client.model.network.TweeterRemoteException;
import edu.byu.cs.tweeter.client.model.network.request.RegisterRequest;
import edu.byu.cs.tweeter.client.model.network.response.RegisterResponse;
import edu.byu.cs.tweeter.model.domain.User;

public class RegisterTest {
    @Test
    public void testRegister() throws IOException, TweeterRemoteException {
        RegisterRequest request = new RegisterRequest("@test", "test", "test", "test", "test");
        ServerFacade serverFacade = new ServerFacade();
        RegisterResponse response = serverFacade.register(request, "/register");

        User user = new User("test", "test", "@test", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        assert(response.isSuccess());
        assert(response.getUser() != null);
        assert(response.getUser().equals(user));
        assert(response.getAuthToken() != null);
    }
}
