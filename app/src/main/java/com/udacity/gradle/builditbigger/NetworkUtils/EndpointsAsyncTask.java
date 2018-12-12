package com.udacity.gradle.builditbigger.NetworkUtils;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import notex.android.blackcoder.com.displayjokeandroid.DisplayJokeActivity;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private boolean showAds;

    EndpointsAsyncTask(boolean showAds) {
        this.showAds = showAds;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
        context = pairs[0].first;
        String name = pairs[0].second;
        try {
            return myApiService.provideJoke().execute().getData();
        }catch (IOException e){
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
//        Open DisplayJoke Activity if its free or paid version
        Intent intent = new Intent(context, DisplayJokeActivity.class);
        String KEY_JOKE = "joke";
//      Result is Joke from endpoint-determines whether to show ads or not
        if (showAds) {

        } else {

            intent.putExtra(KEY_JOKE, result);
            context.startActivity(intent);
        }
    }
}
