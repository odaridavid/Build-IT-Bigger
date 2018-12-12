package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.udacity.gradle.builditbigger.NetworkUtils.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AsyncEndpointsTest {
    @Test
    public void verifyAsyncReturnsJokes() {

    }

    Context context;

    @Test
    public void testVerifyJokeFreeVersion() throws InterruptedException {
        assertTrue(true);
        final CountDownLatch latch = new CountDownLatch(1);
        context = InstrumentationRegistry.getContext();
        EndpointsAsyncTask testTask = new EndpointsAsyncTask(context, true) {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                if (result != null) {
                    assertTrue(result.length() > 0);
                    Log.d("TestCaseAds:", "onPostExecute: " + result);
                    latch.countDown();
                }
            }
        };
        testTask.execute();
        latch.await();
    }

    @Test
    public void testVerifyJokePaidVersion() throws InterruptedException {
        assertTrue(true);
        final CountDownLatch latch = new CountDownLatch(1);
        context = InstrumentationRegistry.getContext();
        EndpointsAsyncTask testTask = new EndpointsAsyncTask(context, false) {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                if (result != null) {
                    assertTrue(result.length() > 0);
                    Log.d("TestCaseAds:", "onPostExecute: " + result);
                    latch.countDown();
                }
            }
        };
        testTask.execute();
        latch.await();
    }
}
