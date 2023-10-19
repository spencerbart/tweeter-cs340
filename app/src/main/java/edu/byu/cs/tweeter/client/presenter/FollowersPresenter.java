package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.services.FollowersService;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFollowersTask;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowersPresenter extends PagedPresenter<User, FollowersService, GetFollowersTask> {
    public interface FollowersView extends PagedView<User> {}

    public FollowersPresenter(FollowersView view, User user) {
        super(view, user);
        this.errorMessage = "followers";
    }

    @Override
    public FollowersService getService() {
        return new FollowersService();
    }
}
