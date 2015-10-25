package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import com.joke.Joke;

import java.util.concurrent.CountDownLatch;

/**
 * Created by teddydoll on 10/23/15.
 */
public class EndpointAndroidTest extends AndroidTestCase {

    public void testEndpointAsyncTask() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        new EndpointsAsyncTask(new EndpointsAsyncTask.EndpointsAsyncTaskListener() {
            @Override
            public void onJoke(Joke joke) {
                assertNotNull(joke);
                signal.countDown();
            }
        }).execute();
        signal.await();
    }
}
