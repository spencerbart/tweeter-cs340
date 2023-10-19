package edu.byu.cs.tweeter.client.model.services;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.BackgroundTaskUtils;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.PagedObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public abstract class PagedService<T, A extends Runnable> {
    public void getItems(AuthToken authToken, User user, int limit, T lastItem, final PagedObserver<T> observer) {
        A task = getTask(authToken, user, limit, lastItem, observer);
        BackgroundTaskUtils.runTask(task);
    }

    protected abstract A getTask(AuthToken authToken, User user, int limit, T lastItem, PagedObserver<T> observer);
}