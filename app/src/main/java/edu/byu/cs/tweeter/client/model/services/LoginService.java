package edu.byu.cs.tweeter.client.model.services;

import android.os.Message;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.BackgroundTaskUtils;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.LoginTask;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class LoginService extends BaseService<LoginService.LoginObserver, LoginService.LoginRequest> {

    @Override
    public void executeService(LoginRequest request, LoginObserver observer) {
        LoginTask loginTask = new LoginTask(request.alias,
                request.password,
                new LoginHandler(observer));
        BackgroundTaskUtils.runTask(loginTask);
    }

    public interface LoginObserver {
        void loginSucceeded(AuthToken authToken, User user);

        void loginFailed(String message);
    }

    public static class LoginRequest {
        public final String alias;
        public final String password;

        public LoginRequest(String alias, String password) {
            this.alias = alias;
            this.password = password;
        }
    }

    public class LoginHandler extends BaseServiceHandler {
        public LoginHandler(LoginObserver observer) {
            super(observer, "Failed to login: ", "Failed to login because of exception: ");
        }


        @Override
        protected void handleSuccess(Message msg) {
            User loggedInUser = (User) msg.getData().getSerializable(LoginTask.USER_KEY);
            AuthToken authToken = (AuthToken) msg.getData().getSerializable(LoginTask.AUTH_TOKEN_KEY);

            // Cache user session information
            Cache.getInstance().setCurrUser(loggedInUser);
            Cache.getInstance().setCurrUserAuthToken(authToken);

            observer.loginSucceeded(authToken, loggedInUser);
        }

        @Override
        protected void handleFailure(String message) {
            observer.loginFailed(message);
        }
    }
}
