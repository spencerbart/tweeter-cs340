package edu.byu.cs.tweeter.client.model.services;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.BackgroundTaskUtils;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.FollowTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.UnfollowTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.ToggleFollowHandler;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.ToggleFollowObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public abstract class ToggleFollowService {
    public void toggle(AuthToken authToken, User user, boolean follow, final ToggleFollowObserver observer) {
        Runnable task;
        if (follow) {
            task = new FollowTask(authToken, user, new ToggleFollowHandler(observer));
        } else {
            task = new UnfollowTask(authToken, user, new ToggleFollowHandler(observer));
        }
        BackgroundTaskUtils.runTask(task);
    }

}
