package edu.byu.cs.tweeter.client.model.services;

import android.os.Message;

import java.util.List;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.BackgroundTaskUtils;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFeedTask;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class FeedService extends BaseService<FeedService.FeedResult, FeedService.FeedRequest> {
    @Override
    public void executeService(FeedRequest request, BaseObserver<FeedResult> observer) {
        GetFeedTask getFeedTask = new GetFeedTask(
                request.authToken,
                request.user,
                request.pageSize,
                request.lastStatus,
                new FeedServiceHandler(observer, "Failed to get feed: ", "Failed to get feed because of exception: ")
        );
        BackgroundTaskUtils.runTask(getFeedTask);
    }

    public interface FeedObserver extends BaseObserver<FeedResult> {}

    public static class FeedResult {
        public final List<Status> statuses;
        public final boolean hasMorePages;
        public final Status lastStatus;

        public FeedResult(List<Status> statuses, boolean hasMorePages, Status lastStatus) {
            this.statuses = statuses;
            this.hasMorePages = hasMorePages;
            this.lastStatus = lastStatus;
        }
    }

    public static class FeedRequest {
        public final AuthToken authToken;
        public final User user;
        public final int pageSize;
        public final Status lastStatus;

        public FeedRequest(AuthToken authToken, User user, int pageSize, Status lastStatus) {
            this.authToken = authToken;
            this.user = user;
            this.pageSize = pageSize;
            this.lastStatus = lastStatus;
        }
    }

    public class FeedServiceHandler extends BaseServiceHandler {
        public FeedServiceHandler(BaseObserver<FeedResult> observer, String failureMessage, String exceptionMessage) {
            super(observer, failureMessage, exceptionMessage);
        }

        @Override
        protected void handleSuccess(Message msg) {
            List<Status> statuses = (List<Status>) msg.getData().getSerializable(GetFeedTask.ITEMS_KEY);
            boolean hasMorePages = msg.getData().getBoolean(GetFeedTask.MORE_PAGES_KEY);
            assert statuses != null;
            Status lastStatus = (statuses.size() > 0) ? statuses.get(statuses.size() - 1) : null;
            observer.handleSuccess(new FeedResult(statuses, hasMorePages, lastStatus));
        }
    }
}
