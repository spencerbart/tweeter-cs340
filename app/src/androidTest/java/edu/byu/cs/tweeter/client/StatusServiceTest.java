package edu.byu.cs.tweeter.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;

import android.os.Looper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.byu.cs.tweeter.client.model.services.StatusService;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.PagedObserver;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class StatusServiceTest {

    @Mock
    private PagedObserver<Status> observer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStory() {
        // mock the PagedOvserver<Status> and pass it to the service
        // verify that the service returns the correct number of statuses
        StatusService service = new StatusService();

        User user = new User("test", "test", "@test", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        service.getStory(user, 10, null, observer);

        // assert that the observer method handleSuccess() is called
        verify(observer).handleSuccess(any(), anyBoolean(), any());
    }
}
