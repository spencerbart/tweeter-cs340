package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * BackgroundTaskUtils contains utility methods needed by background tasks.
 */
public class BackgroundTaskUtils {

    public static void runTask(Runnable task) {
        try {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(task);
        } catch (Exception e) {
            System.err.println("Error executing task: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
