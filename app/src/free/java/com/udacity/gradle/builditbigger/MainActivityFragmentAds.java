package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import notex.android.blackcoder.com.displayjokeandroid.DisplayJokeActivity;
import notex.android.blackcoder.com.jokeproviderjava.JokeProvider;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragmentAds extends Fragment {

    public MainActivityFragmentAds() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
//        Initialize Ads - Sample App Id
        MobileAds.initialize(inflater.getContext(), "ca-app-pub-3940256099942544~3347511713");
//        Interstitial Ad - Full Screen Ad
        final InterstitialAd mInterstitialAd = new InterstitialAd(inflater.getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
//        Find Views in Fragment Xml
        AdView mAdView = root.findViewById(R.id.adView);
        Button btnJoke = root.findViewById(R.id.btn_joke_launch);
        btnJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Display Interstiial Ad when navigating back from display activity
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    tellJoke();
                } else {
                    Toast.makeText(getContext(), "Ad Not Loaded Yet", Toast.LENGTH_LONG).show();
                    tellJoke();
                }
            }
        });
//        Load new Ad
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
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

    public void tellJoke() {
//        Java Library Class instance
        JokeProvider jokeProvider = new JokeProvider();
        Intent displayJokeIntent = new Intent(getActivity(), DisplayJokeActivity.class);
//        Put joke from library as extra and pass to display activity
        String KEY_JOKE = "joke";
        displayJokeIntent.putExtra(KEY_JOKE, jokeProvider.jokeResponse());
//        Start Display Activity
        startActivity(displayJokeIntent);
    }

}
