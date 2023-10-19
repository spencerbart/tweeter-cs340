package edu.byu.cs.tweeter.client.presenter;

import java.util.List;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.services.PagedService;
import edu.byu.cs.tweeter.client.model.services.UserService;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.PagedObserver;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.UserObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public abstract class PagedPresenter<T, S extends PagedService<T, A>, A extends Runnable> extends Presenter implements PagedObserver<T>, UserObserver { // T is going to be Status or User and S is going to be a Service
    protected int PAGE_SIZE = 10;
    @Override
    public void handleSuccess(List<T> items, boolean hasMorePages, T lastItem) {
        this.lastItem = lastItem;
        this.hasMorePages = hasMorePages;
        this.isLoading = false;
        view.endLoadingBottom();
        view.addItems(items);
    }
    @Override
    public void handleFailure(String message) {
        this.isLoading = false;
        view.endLoadingBottom();
        view.showErrorMessage("Failed to get " + this.errorMessage + ": " + message);
    }

    @Override
    public void handleException(Exception exception) {
        this.isLoading = false;
        view.endLoadingBottom();
        view.showErrorMessage("Failed to get " + this.errorMessage + " because of exception: " + exception.getMessage());
    }
    @Override
    public void getUserSucceeded(User user) {
        view.openView(user);
    }
    public interface PagedView<U> extends View {
        void startLoadingBottom();
        void endLoadingBottom();

        void addItems(List<U> items);
    }

    protected PagedView<T> view;
    protected User user;
    protected T lastItem;
    protected boolean hasMorePages;
    protected boolean isLoading = false;

    public PagedPresenter(PagedView<T> view, User user) {
        super(view);
        this.user = user;
    }

    public void getUser(AuthToken authToken, String alias) {
        var userService = new UserService();
        userService.getUser(authToken, alias, this);
        view.showInfoMessage("Geting user's profile...");
    }

    public void loadMoreItems() {
        if (!isLoading) {
            isLoading = true;
            view.startLoadingBottom();

            var service = getService();
            service.getItems(Cache.getInstance().getCurrUserAuthToken(), user, PAGE_SIZE, lastItem, this);
        }
    }

    public boolean getIsLoading() {
        return isLoading;
    }

    public boolean getHasMorePages() {
        return hasMorePages;
    }

    public abstract S getService();
}
