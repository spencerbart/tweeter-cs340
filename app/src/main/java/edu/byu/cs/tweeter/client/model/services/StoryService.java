package edu.byu.cs.tweeter.client.model.services;


import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetStoryTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.PagedHandler;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.PagedObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class StoryService extends PagedService<Status, GetStoryTask> {
    @Override
    protected GetStoryTask getTask(AuthToken authToken, User targetUser, int limit, Status lastStatus, PagedObserver<Status> observer) {
        return new GetStoryTask(authToken, targetUser, limit, lastStatus, new StoryHandler(observer));
    }

    private static class StoryHandler extends PagedHandler<Status> {
        public StoryHandler(PagedObserver<Status> observer) {
            super(observer);
        }
    }

}