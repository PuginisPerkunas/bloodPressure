package buttons.games.sounds.bloodpressure;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class TestStart extends AppCompatActivity {

    Intent mIntent;

    private InterstitialAd mInterstitialAd;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_start);

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

        LinearLayout imageView = (LinearLayout) findViewById(R.id.imageView_fingerPlace);
        final View bar = findViewById(R.id.bar);
        final Animation animation = AnimationUtils.loadAnimation(TestStart.this, R.anim.scan_animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                bar.setVisibility(View.GONE);
                mIntent = new Intent(TestStart.this, ResultActivity.class);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(mIntent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bar.setVisibility(View.VISIBLE);
                bar.startAnimation(animation);
                return false;
            }
        });

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
