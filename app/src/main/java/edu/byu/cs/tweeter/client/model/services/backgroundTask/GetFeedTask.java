package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Handler;

import java.util.List;

import edu.byu.cs.tweeter.client.model.network.request.GetFeedRequest;
import edu.byu.cs.tweeter.client.model.network.response.GetFeedResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.Pair;

public class GetFeedTask extends PagedStatusTask {
    public GetFeedTask(AuthToken authToken, User targetUser, int limit, Status lastStatus,
                       Handler messageHandler) {
        super(authToken, targetUser, limit, lastStatus, messageHandler);
    }

    @Override
    protected Pair<List<Status>, Boolean> getItems() {
//        return getFakeData().getPageOfStatus(getLastItem(), getLimit());
        try {
            long currentTimestamp = System.currentTimeMillis();
            GetFeedRequest request = new GetFeedRequest(getAuthToken(), getTargetUser().getAlias(),
                    getLimit(), getLastItem() == null ? currentTimestamp : getLastItem().getTimestamp());
            GetFeedResponse response = getServerFacade().getFeed(request, "/feed");

            if (response.isSuccess()) {
                return new Pair<>(response.getStatuses(), response.getHasMorePages());
            } else {
//                sendFailedMessage(response.getMessage());
//                return new Pair<>(null, false);
                throw new Exception(response.getMessage());
            }
        } catch (Exception e) {
            sendExceptionMessage(e);
            return new Pair<>(null, false);
        }
    }
}