package edu.byu.cs.tweeter.client.model.services.backgroundTask.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.observer.ToggleFollowObserver;

public class ToggleFollowHandler extends BackgroundTaskHandler<ToggleFollowObserver> {

    public ToggleFollowHandler(ToggleFollowObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(ToggleFollowObserver observer, Bundle data) {
        observer.toggleFollowSucceeded();
    }
}
