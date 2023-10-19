package edu.byu.cs.tweeter.client.presenter;


import edu.byu.cs.tweeter.client.model.services.StoryService;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetStoryTask;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class StoryPresenter extends PagedPresenter<Status, StoryService, GetStoryTask> {
    public interface StoryView extends PagedView<Status> {}

    public StoryPresenter(StoryView view, User user) {
        super(view, user);
        this.errorMessage = "story";
    }

    @Override
    public StoryService getService() {
        return new StoryService();
    }
}
