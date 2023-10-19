package edu.byu.cs.tweeter.client.model.services;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFeedTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.PagedHandler;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.PagedObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class FeedService extends PagedService<Status, GetFeedTask> {
    @Override
    protected GetFeedTask getTask(AuthToken authToken, User targetUser, int limit, Status lastStatus, PagedObserver<Status> observer) {
        return new GetFeedTask(authToken, targetUser, limit, lastStatus, new FeedHandler(observer));
    }

    private static class FeedHandler extends PagedHandler<Status> {
        public FeedHandler(PagedObserver<Status> observer) {
            super(observer);
        }
    }
}
