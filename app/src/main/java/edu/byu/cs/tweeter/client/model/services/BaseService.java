package edu.byu.cs.tweeter.client.model.services;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.BackgroundTask;

public abstract class BaseService<T, R> {
    protected abstract void executeService(R request, BaseObserver<T> observer);

    public interface BaseObserver<T> {
        void handleSuccess(T result);
        void handleFailure(String message);
    }

    protected abstract class BaseServiceHandler extends Handler {
        protected final BaseObserver<T> observer;
        protected String failureMessage;
        protected String exceptionMessage;

        public BaseServiceHandler(BaseObserver<T> observer, String failureMessage, String exceptionMessage) {
            super(Looper.getMainLooper());
            this.observer = observer;
            this.failureMessage = failureMessage;
            this.exceptionMessage = exceptionMessage;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            boolean success = msg.getData().getBoolean(BackgroundTask.SUCCESS_KEY);
            if (success) {
                handleSuccess(msg);
            } else if (msg.getData().containsKey(BackgroundTask.MESSAGE_KEY)) {
                String message = msg.getData().getString(BackgroundTask.MESSAGE_KEY);
                observer.handleFailure(this.failureMessage + message);
            } else if (msg.getData().containsKey(BackgroundTask.EXCEPTION_KEY)) {
                Exception ex = (Exception) msg.getData().getSerializable(BackgroundTask.EXCEPTION_KEY);
                assert ex != null;
                observer.handleFailure(this.exceptionMessage + ex.getMessage());
            }
        }
        protected abstract void handleSuccess(Message msg);
    }
}
