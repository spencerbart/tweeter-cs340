package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.services.StatusService;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class FeedPresenter extends PagedPresenter<Status> {
    @Override
    protected void getItems() {
        StatusService service = new StatusService();
        this.errorMessage = "feed";
        service.getFeed(user, PAGE_SIZE, lastItem, this);
    }

    public interface FeedView extends PagedView<Status> {}

    public FeedPresenter(FeedView view, User user) {
        super(view, user);
    }

}
