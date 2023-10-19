package edu.byu.cs.tweeter.client.model.services;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.BackgroundTaskUtils;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetUserTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.UserHandler;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.UserObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;

public class UserService {
    public void getUser(AuthToken authToken, String alias, final UserObserver observer) {
        GetUserTask task = new GetUserTask(authToken, alias, new GetUserHandler(observer));
        BackgroundTaskUtils.runTask(task);
    }

    private static class GetUserHandler extends UserHandler {
        public GetUserHandler(UserObserver observer) {
            super(observer);
        }
    }

}
