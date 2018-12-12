package notex.android.blackcoder.com.displayjokeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Locale;

public class DisplayJokeActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
        textView = findViewById(R.id.tv_joke);
//        Get Intent
        String KEY_JOKE = "joke";
        String KEY_FREE = "free_version";
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            final String joke = String.format("%s", intent.getStringExtra(KEY_JOKE));
            if (intent.hasExtra(KEY_JOKE) && intent.hasExtra(KEY_FREE)) {
                Log.d("Main Activity", "IsFree: True");
                //            For Free Version
                MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
                //        Interstitial Ad - Full Screen Ad
                final InterstitialAd mInterstitialAd = new InterstitialAd(this);
                mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        mInterstitialAd.show();
                        textView.setText(joke);
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();

                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
//                        If ad doesn't load
                        Toast.makeText(DisplayJokeActivity.this, "Ad Not Loaded", Toast.LENGTH_LONG).show();
                       String err = String.format(Locale.getDefault(),"View Ad To See Joke:\nError Code %d",i);
                        textView.setText(err);
                    }
                });


            } else if (intent.hasExtra(KEY_JOKE)) {
                //            Display Joke if intent has joke for Paid Version-no ads
                textView.setText(joke);
            }
        }
    }
}
