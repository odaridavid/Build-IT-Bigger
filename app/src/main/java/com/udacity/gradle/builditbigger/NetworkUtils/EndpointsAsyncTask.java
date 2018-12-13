package com.udacity.gradle.builditbigger.NetworkUtils;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<IJokeLoadedInterface, Void, String> {
    //    Interface for easier testing
    private IJokeLoadedInterface iJokeLoadedInterface;
    private static MyApi myApiService = null;
    private String TAG = EndpointsAsyncTask.class.getSimpleName();
    @Override
    protected String doInBackground(IJokeLoadedInterface... iJokeLoadedInterfaces) {
        final String ROOT_URL_LOCALHOST = "http://10.0.2.2:8080/_ah/api/";
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(ROOT_URL_LOCALHOST)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
//        Assign Listener on Async Task Call
        iJokeLoadedInterface = iJokeLoadedInterfaces[0];
        try {
            return myApiService.provideJoke().execute().getData();
        }catch (IOException e){
            Log.d(TAG, e.getMessage());
//            Returns empty string on exception received
            return "";
        }
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (this.iJokeLoadedInterface != null) {
            this.iJokeLoadedInterface.jokeLoaded(result);
        }
    }
}
