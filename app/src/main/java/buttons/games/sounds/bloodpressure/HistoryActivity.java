package buttons.games.sounds.bloodpressure;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    SharedPreferences mPrefs ;
    ListView mListViewRecords;
    private AdView mAdView;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5116262951762775/6875545484");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

        mListViewRecords = findViewById(R.id.listView_records);
        mPrefs = getSharedPreferences(Constants.SP_KEY, MODE_PRIVATE);
        List<String> recordsList = getRecordsList();
        if(recordsList.isEmpty()){
            recordsList.add("No data records");
        } else {

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.item_record,
                recordsList );

        mListViewRecords.setAdapter(arrayAdapter);

    }

    public List<String> getRecordsList(){
        Gson gson = new Gson();
        String json = mPrefs.getString(Constants.SP_COMMISSION_OBJECT_KEY, Constants.VALUE_DEFAULT_STRING);

        if(json.equals(""))
        {
            return new ArrayList<String>();
        } else {
            return new ArrayList<String>(Arrays.asList(gson.fromJson(json, String[].class)));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        finish();
    }
}
