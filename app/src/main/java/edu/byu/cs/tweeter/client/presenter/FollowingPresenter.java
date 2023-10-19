package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.services.FollowingService;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFollowingTask;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowingPresenter extends PagedPresenter<User, FollowingService, GetFollowingTask> {
    public interface FollowingView extends PagedView<User> {}

    public FollowingPresenter(FollowingView view, User user) {
        super(view, user);
        this.errorMessage = "following";
    }

    @Override
    public FollowingService getService() {
        return new FollowingService();
    }
}

