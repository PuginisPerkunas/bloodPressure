package buttons.games.sounds.bloodpressure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    TextView resultText;
    Random rand = new Random();
    int n = 99;
    SimpleDateFormat sdf;
    String currentDateAndTime;
    SharedPreferences mPrefs ;
    Button historyBtn ;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mPrefs = getSharedPreferences(Constants.SP_KEY, MODE_PRIVATE);
        historyBtn = findViewById(R.id.history_btn);
        n = rand.nextInt(160 + 1 - 85) + 85;
        resultText = findViewById(R.id.result_text);
        resultText.setText(String.valueOf(n));

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentDateAndTime = sdf.format(new Date());

        saveResult(String.valueOf(n), currentDateAndTime);

        historyBtn.setOnClickListener(v ->{
            Intent mIntent = new Intent(ResultActivity.this, HistoryActivity.class);
            startActivity(mIntent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void saveResult(String amount, String currentDateAndTime) {
        Gson gson = new Gson();
        String result = currentDateAndTime + " - " + String.valueOf(amount) + "mg/dL";
        List<String> items = getRecordsList();
        items.add(result);

        String json = gson.toJson(items);
        mPrefs.edit().putString(Constants.SP_COMMISSION_OBJECT_KEY, json).apply();
        Log.d("ITEMS",json);
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

}
