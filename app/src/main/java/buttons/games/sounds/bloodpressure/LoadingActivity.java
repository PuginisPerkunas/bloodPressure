package buttons.games.sounds.bloodpressure;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class LoadingActivity extends AppCompatActivity {

    private int myProgresBarStatus = 0;
    private Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        ImageView imageView=(ImageView)findViewById(R.id.centerImage);
        rippleBackground.startRippleAnimation();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (myProgresBarStatus < 100){
                    myProgresBarStatus++;
                    android.os.SystemClock.sleep(30);
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {

//                        myText.setText("Loaded");
//                        if (mInterstitialAd.isLoaded()) {
//                            mInterstitialAd.show();
//                        } else {
                            Intent toGame = new Intent(LoadingActivity.this,MainActivity.class);
                            startActivity(toGame);
                            finish();
                       // }
                    }
                });
            }
        }).start();

    }
}
