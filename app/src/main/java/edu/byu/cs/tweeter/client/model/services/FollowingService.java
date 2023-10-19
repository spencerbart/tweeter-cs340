package edu.byu.cs.tweeter.client.model.services;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.BackgroundTaskUtils;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFollowingTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.PagedHandler;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.PagedObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowingService extends PagedService<User, GetFollowingTask> {

    @Override
    protected GetFollowingTask getTask(AuthToken authToken, User user, int limit, User lastItem, PagedObserver<User> observer) {
        return new GetFollowingTask(authToken, user, limit, lastItem, new FollowingHandler(observer));
    }

    private static class FollowingHandler extends PagedHandler<User> {
        public FollowingHandler(PagedObserver<User> observer) {
            super(observer);
        }
    }
}
