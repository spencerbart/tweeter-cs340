package edu.byu.cs.tweeter.client.presenter;

import java.util.List;

import edu.byu.cs.tweeter.client.model.services.FeedService;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFeedTask;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class FeedPresenter extends PagedPresenter<Status, FeedService, GetFeedTask> {
    public interface FeedView extends PagedView<Status> {}

    public FeedPresenter(FeedView view, User user) {
        super(view, user);
        this.errorMessage = "feed";
    }

    @Override
    public FeedService getService() {
        return new FeedService();
    }
}
