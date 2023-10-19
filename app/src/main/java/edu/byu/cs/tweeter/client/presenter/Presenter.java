package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.model.domain.User;

public abstract class Presenter {
    public interface View {
        void showInfoMessage(String message);
        void showErrorMessage(String message);

        void openView(User user);
    }

    protected View view;
    protected String errorMessage;
    public Presenter(View view) {
        this.view = view;
    }

}
