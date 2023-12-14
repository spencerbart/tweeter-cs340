package edu.byu.cs.tweeter.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.services.StatusService;
import edu.byu.cs.tweeter.client.model.services.UserService;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.AuthenticateObserver;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.PagedObserver;
import edu.byu.cs.tweeter.client.presenter.MainPresenter;
import edu.byu.cs.tweeter.client.utils.StatusUtil;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class FullTest {

    private TempAuthenticateObserver observer;

    private TempView view;

    @Spy
    private MainPresenter presenter;

    @Spy
    private TempPageObserver pagedObserver;

    private Status status;

    private CountDownLatch countDownLatch;

    @BeforeEach
    public void setup() {
        resetCountDownLatch();
        observer = new TempAuthenticateObserver();
        pagedObserver = new TempPageObserver();
        view = new TempView();
        MockitoAnnotations.openMocks(this);
    }

    private void resetCountDownLatch() {
        countDownLatch = new CountDownLatch(1);
    }

    private void awaitCountDownLatch() throws InterruptedException {
        countDownLatch.await();
        resetCountDownLatch();
    }

    private class TempAuthenticateObserver implements AuthenticateObserver {

        @Override
        public void authenticateSucceeded(AuthToken authToken, User user) {
            countDownLatch.countDown();
        }

        @Override
        public void handleFailure(String message) {
            countDownLatch.countDown();
        }

        @Override
        public void handleException(Exception exception) {
            countDownLatch.countDown();
        }
    }

    private class TempPageObserver implements PagedObserver<Status> {
        public List<Status> statuses;
        public Status lastStatus;

        @Override
        public void handleFailure(String message) {
            countDownLatch.countDown();
        }

        @Override
        public void handleException(Exception exception) {
            countDownLatch.countDown();
        }

        @Override
        public void handleSuccess(List<Status> items, boolean hasMorePages, Status lastItem) {
            countDownLatch.countDown();
            statuses = items;
            lastStatus = lastItem;
        }
    }

    private class TempView implements MainPresenter.View {

        @Override
        public void loginActivity() {

        }

        @Override
        public void updateFollowButton(boolean removed) {

        }

        @Override
        public void followButtonSetEnabled() {

        }

        @Override
        public void isFollower(boolean isFollower) {

        }

        @Override
        public void setFollowersCount(int followersCount) {

        }

        @Override
        public void setFollowingCount(int followingCount) {

        }

        @Override
        public void showInfoMessage(String message) {
            if (message.equals("Successfully Posted!")) {
                countDownLatch.countDown();
            }
        }

        @Override
        public void hideInfoMessage() {

        }

        @Override
        public void showErrorMessage(String message) {
        }

        @Override
        public void hideErrorMessage() {

        }

        @Override
        public void openView(User user) {

        }
    }

    @Test
    public void testLoginUser() {
        UserService service = new UserService();

        service.login("@new", "password", observer);

        try {
            awaitCountDownLatch();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        resetCountDownLatch();

        User user = Cache.getInstance().getCurrUser();

        presenter = new MainPresenter(view, user);

        var timestamp = System.currentTimeMillis();

        status = new Status("654", user, timestamp, StatusUtil.parseURLs("test"), StatusUtil.parseMentions("test"));
        presenter.postStatus("654", user, timestamp);

        try {
            awaitCountDownLatch();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        resetCountDownLatch();

        // retrieve the user's story and check if the status was posted
        StatusService statusService = new StatusService();

        statusService.getStory(user, 5, null, pagedObserver);

        try {
            awaitCountDownLatch();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(pagedObserver).handleSuccess(any(), anyBoolean(), any());

        var isTrue = pagedObserver.statuses.get(0).equals(status);
        assert(isTrue);
    }
}
