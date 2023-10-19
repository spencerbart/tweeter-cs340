package edu.byu.cs.tweeter.client.model.services;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetStoryTask;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class StoryService extends BaseService<StoryService.GetStoryObserver, StoryService.GetStoryRequest> {
    public interface GetStoryObserver {
        void getStorySucceeded(List<Status> statuses, boolean hasMorePages, Status lastStatus);

        void getStoryFailed(String message);
    }

    public void executeService(GetStoryRequest request, GetStoryObserver observer) {
        GetStoryTask getStoryTask = new GetStoryTask(
                request.authToken,
                request.user,
                request.pageSize,
                request.lastStatus,
                new GetStoryHandler(observer)
        );
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(getStoryTask);
    }

    public static class GetStoryRequest {
        public final AuthToken authToken;
        public final User user;
        public final int pageSize;
        public final Status lastStatus;

        public GetStoryRequest(AuthToken authToken, User user, int pageSize, Status lastStatus) {
            this.authToken = authToken;
            this.user = user;
            this.pageSize = pageSize;
            this.lastStatus = lastStatus;
        }
    }

    public class GetStoryHandler extends BaseServiceHandler {
        public GetStoryHandler(GetStoryObserver observer) {
            super(observer, "Failed to get story: ", "Failed to get story because of exception: ");
        }

        @Override
        protected void handleSuccess(Message msg) {
            List<Status> statuses = (List<Status>) msg.getData().getSerializable(GetStoryTask.ITEMS_KEY);
            boolean hasMorePages = msg.getData().getBoolean(GetStoryTask.MORE_PAGES_KEY);
            assert statuses != null;
            Status lastStatus = (statuses.size() > 0) ? statuses.get(statuses.size() - 1) : null;
            observer.getStorySucceeded(statuses, hasMorePages, lastStatus);
        }

        @Override
        protected void handleFailure(String message) {
            observer.getStoryFailed(message);
        }
    }

}
