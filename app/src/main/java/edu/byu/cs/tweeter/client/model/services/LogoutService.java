package edu.byu.cs.tweeter.client.model.services;

import android.os.Message;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.BackgroundTaskUtils;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.LogoutTask;

public class LogoutService extends BaseService<LogoutService.LogoutObserver, String> {
    @Override
    public void executeService(String request, LogoutObserver observer) {
        LogoutTask logoutTask = new LogoutTask(Cache.getInstance().getCurrUserAuthToken(), new LogoutHandler(observer));
        BackgroundTaskUtils.runTask(logoutTask);
    }

    public interface LogoutObserver {
        void logoutSucceeded();
        void logoutFailed(String message);
    }

    public class LogoutHandler extends BaseServiceHandler {
        public LogoutHandler(LogoutObserver observer) {
            super(observer, "Failed to logout: ", "Failed to logout because of exception: ");
        }

        @Override
        protected void handleSuccess(Message msg) {
            observer.logoutSucceeded();
        }

        @Override
        protected void handleFailure(String message) {
            observer.logoutFailed(message);
        }
    }

}
