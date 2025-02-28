package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.services.FollowService;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowersPresenter extends PagedPresenter<User> {
    @Override
    protected void getItems() {
        FollowService service = new FollowService();
        this.errorMessage = "followers";
        service.getFollowers(user, PAGE_SIZE, lastItem, this);
    }

    public interface FollowersView extends PagedView<User> {}

    public FollowersPresenter(FollowersView view, User user) {
        super(view, user);
    }
}
