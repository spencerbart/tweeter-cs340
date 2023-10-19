package edu.byu.cs.tweeter.client.model.services;

import android.os.Message;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.BackgroundTaskUtils;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetUserTask;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class UserService extends BaseService<UserService.GetUserObserver, UserService.GetUserRequest> {
    public interface GetUserObserver {
        void getUserSucceeded(User user);
        void getUserFailed(String message);
    }

    @Override
    public void executeService(GetUserRequest request, GetUserObserver observer) {
        GetUserTask getUserTask = new GetUserTask(request.authToken, request.alias, new GetUserHandler(observer));
        BackgroundTaskUtils.runTask(getUserTask);
    }

    public static class GetUserRequest {
        public final AuthToken authToken;
        public final String alias;

        public GetUserRequest(AuthToken authToken, String alias) {
            this.authToken = authToken;
            this.alias = alias;
        }
    }

    /**
     * Message handler (i.e., observer) for GetUserTask.
     */
    private class GetUserHandler extends BaseServiceHandler {
        public GetUserHandler(GetUserObserver observer) {
            super(observer, "Failed to get user's profile: ", "Failed to get user's profile because of exception: ");
        }
        @Override
        protected void handleSuccess(Message msg) {
            User user = (User) msg.getData().getSerializable(GetUserTask.USER_KEY);

            observer.getUserSucceeded(user);
        }

        @Override
        protected void handleFailure(String message) {
            observer.getUserFailed(message);
        }

    }
}
