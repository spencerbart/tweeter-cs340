package edu.byu.cs.tweeter.client.model.network.response;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.Status;

public class GetFeedResponse extends PagedResponse {
    private List<Status> statuses;

    public GetFeedResponse(String message) {
        super(false, message, false);
    }
    public GetFeedResponse(List<Status> statuses, boolean hasMorePages) {
        super(true, hasMorePages);
        this.statuses = statuses;
    }

    public List<Status> getStatuses() {
        return statuses;
    }
}
