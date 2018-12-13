package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.NetworkUtils.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.NetworkUtils.IJokeLoadedInterface;

import notex.android.blackcoder.com.displayjokeandroid.DisplayJokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragmentAds extends Fragment {

    public MainActivityFragmentAds() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
//        Initialize Ads - Sample App Id
        MobileAds.initialize(inflater.getContext(), "ca-app-pub-3940256099942544~3347511713");

//        Find Views in Fragment Xml
        AdView mAdView = root.findViewById(R.id.adView);
        Button btnJoke = root.findViewById(R.id.btn_joke_launch);
        btnJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke(inflater.getContext());
            }
        });
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    public void tellJoke(final Context context) {
//        Gets jokes with ads set to true
        new EndpointsAsyncTask(context).execute(new IJokeLoadedInterface() {
            @Override
            public void jokeLoaded(String joke) {
                //        Open DisplayJoke Activity - executed in postExecute
                Intent intent = new Intent(context, DisplayJokeActivity.class);
                String KEY_JOKE = "joke";
                String KEY_FREE = "free_version";
                intent.putExtra(KEY_JOKE, joke);
                intent.putExtra(KEY_FREE, true);
                context.startActivity(intent);
            }
        });
    }
}
