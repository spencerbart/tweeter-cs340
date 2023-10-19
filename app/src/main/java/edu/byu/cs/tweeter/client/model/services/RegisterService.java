package edu.byu.cs.tweeter.client.model.services;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.BackgroundTaskUtils;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.RegisterTask;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class RegisterService extends BaseService<RegisterService.RegisterObserver, RegisterService.RegisterRequest> {
    public interface RegisterObserver {
        void registerSucceeded(User user, AuthToken authToken);

        void registerFailed(String message);
    }

    @Override
    public void executeService(RegisterRequest request, RegisterObserver observer) {
        RegisterTask registerTask = new RegisterTask(
                request.firstName,
                request.lastName,
                request.alias,
                request.password,
                request.imageBytesBase64,
                new RegisterHandler(observer)
        );

        BackgroundTaskUtils.runTask(registerTask);
    }

    public static class RegisterRequest {
        public final String firstName;
        public final String lastName;
        public final String alias;
        public final String password;
        public final String imageBytesBase64;

        public RegisterRequest(String firstName, String lastName, String alias, String password, String imageBytesBase64) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.alias = alias;
            this.password = password;
            this.imageBytesBase64 = imageBytesBase64;
        }
    }
    private class RegisterHandler extends BaseServiceHandler {

        public RegisterHandler(RegisterObserver observer) {
            super(observer, "Failed to register: ", "Failed to register because of exception: ");
        }

        @Override
        protected void handleSuccess(Message msg) {
            User registeredUser = (User) msg.getData().getSerializable(RegisterTask.USER_KEY);
            AuthToken authToken = (AuthToken) msg.getData().getSerializable(RegisterTask.AUTH_TOKEN_KEY);

            Cache.getInstance().setCurrUser(registeredUser);
            Cache.getInstance().setCurrUserAuthToken(authToken);

            observer.registerSucceeded(registeredUser, authToken);
        }

        @Override
        protected void handleFailure(String message) {
            observer.registerFailed(message);
        }
    }
}
