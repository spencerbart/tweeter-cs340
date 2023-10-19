package edu.byu.cs.tweeter.client.model.services;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFollowersTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.PagedHandler;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.PagedObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowersService extends PagedService<User, GetFollowersTask> {

    @Override
    public GetFollowersTask getTask(AuthToken authToken, User targetUser, int limit, User lastItem, PagedObserver<User> observer) {
        return new GetFollowersTask(authToken, targetUser, limit, lastItem, new FollowersHandler(observer));
    }

    private static class FollowersHandler extends PagedHandler<User> {
        public FollowersHandler(PagedObserver<User> observer) {
            super(observer);
        }

    }
}
