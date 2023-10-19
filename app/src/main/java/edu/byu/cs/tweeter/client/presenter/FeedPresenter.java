package edu.byu.cs.tweeter.client.presenter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.services.FeedService;
import edu.byu.cs.tweeter.client.model.services.UserService;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFeedTask;
import edu.byu.cs.tweeter.client.view.main.feed.FeedFragment;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class FeedPresenter implements UserService.GetUserObserver, FeedService.FeedObserver {

    private static final int PAGE_SIZE = 10;

    @Override
    public void getUserSucceeded(User user) {
        view.openMainView(user);
    }

    @Override
    public void getUserFailed(String message) {
        view.showErrorMessage(message);
    }

    @Override
    public void handleSuccess(FeedService.FeedResult result) {
        this.lastStatus = result.lastStatus;
        this.hasMorePages = result.hasMorePages;
        this.isLoading = false;
        view.endingLoad();
        view.addItems(result.statuses);
    }

    @Override
    public void handleFailure(String message) {
        this.isLoading = false;
        view.endingLoad();
        view.showErrorMessage(message);
    }

    public interface View {

        void showInfoMessage(String message);
        void showErrorMessage(String message);

        void openMainView(User user);

        void startingLoad();
        void endingLoad();

        void addItems(List<Status> statuses);
    }

    private View view;
    private User user;
    private Status lastStatus;
    private boolean hasMorePages;
    private boolean isLoading = false;

    public boolean getHasMorePages() {
        return hasMorePages;
    }

    public boolean getIsLoading() {
        return isLoading;
    }

    public FeedPresenter(View view, User user) {
        this.view = view;
        this.user = user;
    }

    public void getUser(AuthToken authToken, String alias) {
        var userService = new UserService();
        userService.getUser(authToken, alias, this);
        view.showInfoMessage("Getting user's profile...");
    }

    public void loadMoreItems() {
        if (!isLoading) {
            isLoading = true;
            view.startingLoad();

            var feedService = new FeedService();
            feedService.executeService(new FeedService.FeedRequest(Cache.getInstance().getCurrUserAuthToken(), user, PAGE_SIZE, lastStatus), this);
        }
    }
}
