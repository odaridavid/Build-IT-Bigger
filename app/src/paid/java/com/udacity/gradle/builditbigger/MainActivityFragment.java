package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udacity.gradle.builditbigger.networkutils.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.networkutils.IJokeLoadedInterface;

import notex.android.blackcoder.com.displayjokeandroid.DisplayJokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button btnJoke = root.findViewById(R.id.btn_joke_launch);
        btnJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke(inflater.getContext());
            }
        });
        return root;
    }

    public void tellJoke(final Context context) {
//        Get jokes with no ads
        new EndpointsAsyncTask().execute(new IJokeLoadedInterface() {
            @Override
            public void jokeLoaded(String joke) {
                //        Open DisplayJoke Activity - executed in postExecute
                Intent intent = new Intent(context, DisplayJokeActivity.class);
                String KEY_JOKE = "joke";
                intent.putExtra(KEY_JOKE, joke);
                context.startActivity(intent);
            }
        });
    }

}
