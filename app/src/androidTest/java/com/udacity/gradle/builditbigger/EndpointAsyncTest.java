package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.networkutils.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.networkutils.IJokeLoadedInterface;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class EndpointAsyncTest {

    private CountDownLatch signal;
    private String mJoke;

    @Test
    public void testJokeRetrivalInAsyncTask() {
        try {
            //   Constructs a CountDownLatch initialized with the given count.
            // initialized with a count of one serves as a simple on/off latch
            signal = new CountDownLatch(1);
            new EndpointsAsyncTask().execute(new IJokeLoadedInterface() {
                @Override
                public void jokeLoaded(String joke) {
                    mJoke = joke;
                    signal.countDown();
                }
            });
//             block until the current count reaches zero
            signal.await(10, TimeUnit.SECONDS);
//            Then Check is not null and is empty should be true for test to pass
            assertNotNull("Null Joke", mJoke);
            assertFalse("Empty Joke", mJoke.isEmpty());
        } catch (Exception ex) {
            fail();
        }
    }
}
