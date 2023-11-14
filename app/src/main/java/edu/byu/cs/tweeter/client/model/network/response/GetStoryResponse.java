package edu.byu.cs.tweeter.client.model.network.response;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.Status;

public class GetStoryResponse extends PagedResponse {
    private List<Status> statuses;
    public GetStoryResponse(boolean success, boolean hasMorePages) {
        super(success, hasMorePages);
    }

    public List<Status> getStatuses() {
        return statuses;
    }
}
