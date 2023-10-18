package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

/**
 * Background task that establishes a following relationship between two users.
 */
public class FollowTask extends AuthenticatedTask {
    /**
     * The user that is being followed.
     */
    private final User followee;

    public FollowTask(AuthToken authToken, User followee, Handler messageHandler) {
        super(authToken, messageHandler);
        this.followee = followee;
    }

    @Override
    protected void runTask() {
        sendSuccessMessage();
    }

//    @Override
//    public void run() {
//        try {
//
//            sendSuccessMessage();
//
//        } catch (Exception ex) {
//            Log.e(LOG_TAG, ex.getMessage(), ex);
//            sendExceptionMessage(ex);
//        }
//    }

//    private void sendSuccessMessage() {
//        Bundle msgBundle = new Bundle();
//        msgBundle.putBoolean(SUCCESS_KEY, true);
//
//        Message msg = Message.obtain();
//        msg.setData(msgBundle);
//
//        messageHandler.sendMessage(msg);
//    }

//    private void sendFailedMessage(String message) {
//        Bundle msgBundle = new Bundle();
//        msgBundle.putBoolean(SUCCESS_KEY, false);
//        msgBundle.putString(MESSAGE_KEY, message);
//
//        Message msg = Message.obtain();
//        msg.setData(msgBundle);
//
//        messageHandler.sendMessage(msg);
//    }

//    private void sendExceptionMessage(Exception exception) {
//        Bundle msgBundle = new Bundle();
//        msgBundle.putBoolean(SUCCESS_KEY, false);
//        msgBundle.putSerializable(EXCEPTION_KEY, exception);
//
//        Message msg = Message.obtain();
//        msg.setData(msgBundle);
//
//        messageHandler.sendMessage(msg);
//    }
}
