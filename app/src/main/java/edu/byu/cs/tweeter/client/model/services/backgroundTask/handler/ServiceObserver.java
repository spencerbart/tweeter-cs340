package edu.byu.cs.tweeter.client.model.services.backgroundTask.handler;

public interface ServiceObserver {
    void handleFailure(String message);
    void handleException(Exception exception);
}
