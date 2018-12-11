package notex.android.blackcoder.com.displayjokeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class DisplayJokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
//        Get Intent
        String KEY_JOKE = "joke";
        Intent intent = getIntent();
        if (intent.hasExtra(KEY_JOKE)) {
//            Display Joke if intent has joke
            Toast.makeText(this, intent.getStringExtra(KEY_JOKE), Toast.LENGTH_LONG).show();
        }
    }
}
