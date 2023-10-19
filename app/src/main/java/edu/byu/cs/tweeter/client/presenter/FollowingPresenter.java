package edu.byu.cs.tweeter.client.presenter;

import java.util.List;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.services.FollowService;
import edu.byu.cs.tweeter.client.model.services.UserService;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowingPresenter implements UserService.GetUserObserver, FollowService.GetFollowingObserver {

    private static final int PAGE_SIZE = 10;



    public interface View {
        void showInfoMessage(String message);
        void showErrorMessage(String message);

        void openMainView(User user);

        void startingLoad();
        void endingLoad();

        void addItems(List<User> followees);
    }

    private View view;
    private User user;
    private User lastFollowee;
    private boolean hasMorePages;
    private boolean isLoading = false;

    public boolean getHasMorePages() {
        return hasMorePages;
    }

    public boolean getIsLoading() {
        return isLoading;
    }

    public FollowingPresenter(View view, User user) {
        this.view = view;
        this.user = user;
    }

    public void getUser(AuthToken authToken, String alias) {
        var userService = new UserService();
        userService.executeService(new UserService.GetUserRequest(authToken, alias), this);
        view.showInfoMessage("Getting user's profile...");
    }

    @Override
    public void getUserSucceeded(User user) {
        view.openMainView(user);
    }

    @Override
    public void getUserFailed(String message) {
        view.showErrorMessage(message);
    }

    @Override
    public void getFollowingSucceeded(List<User> followees, boolean hasMorePages, User lastFollowee) {
        this.lastFollowee = lastFollowee;
        this.hasMorePages = hasMorePages;
        isLoading = false;
        view.endingLoad();
        view.addItems(followees);
    }

    @Override
    public void getFollowingFailed(String message) {
        isLoading = false;
        view.endingLoad();
        view.showErrorMessage(message);
    }

    public void loadMoreItems() {
        if (!isLoading) {   // This guard is important for avoiding a race condition in the scrolling code.
            isLoading = true;
            view.startingLoad();

            var followService = new FollowService();
            followService.getFollowing(Cache.getInstance().getCurrUserAuthToken(), user, PAGE_SIZE, lastFollowee, this);
        }
    }

}

