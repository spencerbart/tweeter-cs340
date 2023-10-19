package edu.byu.cs.tweeter.client.model.services;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.BackgroundTaskUtils;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.FollowTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFollowersCountTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFollowingCountTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.IsFollowerTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.PostStatusTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.UnfollowTask;
import edu.byu.cs.tweeter.client.view.main.MainActivity;
import edu.byu.cs.tweeter.model.domain.Status;

public class PostService extends BaseService<PostService.PostObserver, String> {

    @Override
    public void executeService(String request, PostObserver observer) {
        try {
            Status newStatus = new Status(request, Cache.getInstance().getCurrUser(), System.currentTimeMillis(), parseURLs(request), parseMentions(request));
            PostStatusTask statusTask = new PostStatusTask(
                    Cache.getInstance().getCurrUserAuthToken(),
                    newStatus,
                    new PostStatusHandler(observer)
            );
            BackgroundTaskUtils.runTask(statusTask);
        } catch (Exception ex) {
            observer.postFailed("Failed to post the status because of exception: " + ex.getMessage());
        }
    }

    public interface PostObserver {
        void postSucceeded();
        void postFailed(String message);
    }

    private List<String> parseURLs(String post) {
        List<String> containedUrls = new ArrayList<>();
        for (String word : post.split("\\s")) {
            if (word.startsWith("http://") || word.startsWith("https://")) {

                int index = findUrlEndIndex(word);

                word = word.substring(0, index);

                containedUrls.add(word);
            }
        }

        return containedUrls;
    }

    private int findUrlEndIndex(String word) {
        if (word.contains(".com")) {
            int index = word.indexOf(".com");
            index += 4;
            return index;
        } else if (word.contains(".org")) {
            int index = word.indexOf(".org");
            index += 4;
            return index;
        } else if (word.contains(".edu")) {
            int index = word.indexOf(".edu");
            index += 4;
            return index;
        } else if (word.contains(".net")) {
            int index = word.indexOf(".net");
            index += 4;
            return index;
        } else if (word.contains(".mil")) {
            int index = word.indexOf(".mil");
            index += 4;
            return index;
        } else {
            return word.length();
        }
    }

    public List<String> parseMentions(String post) {
        List<String> containedMentions = new ArrayList<>();

        for (String word : post.split("\\s")) {
            if (word.startsWith("@")) {
                word = word.replaceAll("[^a-zA-Z0-9]", "");
                word = "@".concat(word);

                containedMentions.add(word);
            }
        }

        return containedMentions;
    }

    public class PostStatusHandler extends BaseServiceHandler {

        public PostStatusHandler(PostObserver observer) {
            super(observer, "Failed to post status: ", "Failed to post status because of exception: ");
        }
        @Override
        protected void handleSuccess(Message msg) {
            observer.postSucceeded();
        }

        @Override
        protected void handleFailure(String message) {
            observer.postFailed(message);
        }
    }
}
