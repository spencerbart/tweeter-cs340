package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Handler;

import java.util.List;

import edu.byu.cs.tweeter.client.model.network.request.GetStoryRequest;
import edu.byu.cs.tweeter.client.model.network.response.GetStoryResponse;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.Pair;

public class GetStoryTask extends PagedStatusTask {

    public GetStoryTask(AuthToken authToken, User targetUser, int limit, Status lastStatus,
                        Handler messageHandler) {
        super(authToken, targetUser, limit, lastStatus, messageHandler);
    }

    @Override
    protected Pair<List<Status>, Boolean> getItems() {
//        return getFakeData().getPageOfStatus(getLastItem(), getLimit());
        try {
            GetStoryRequest request = new GetStoryRequest(getAuthToken(), getTargetUser(),
                    getLimit(), getLastItem());
            GetStoryResponse response = getServerFacade().getStory(request, "/story");
            if (response.isSuccess()) {
                return new Pair<>(response.getStatuses(), response.getHasMorePages());
            } else {
                sendFailedMessage(response.getMessage());
            }
        } catch (Exception e) {
            sendExceptionMessage(e);
        }
        return new Pair<>(null, false);
    }
}
