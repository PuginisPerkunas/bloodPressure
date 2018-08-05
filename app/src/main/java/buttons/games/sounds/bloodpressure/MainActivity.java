package buttons.games.sounds.bloodpressure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    Button buttonTest, buttonHowToUse, buttonHistory;
    Intent mIntent;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonTest = (Button) findViewById(R.id.sugarTest);
        buttonHowToUse = (Button) findViewById(R.id.how_to_use);
        buttonHistory = (Button) findViewById(R.id.history);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        buttonTest.setOnClickListener(v ->{
            mIntent = new Intent(MainActivity.this, TestStart.class);
            startActivity(mIntent);
        });
        buttonHistory.setOnClickListener(v ->{
            mIntent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(mIntent);
        });
        buttonHowToUse.setOnClickListener(v ->{
            mIntent = new Intent(MainActivity.this, HowToUse.class);
            startActivity(mIntent);
        });
    }
}
