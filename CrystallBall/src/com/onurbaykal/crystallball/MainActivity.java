package com.onurbaykal.crystallball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.onurbaykal.crystallball.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity {
	
	private ImageView mBackImageView;
	private TextView mAnswerLabel;
	private Button mAnswerButton;
	private MediaPlayer mPlayer;
	private boolean mIsAnimating;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;
	private AnimationDrawable mBallAnimation;
	private AlphaAnimation mFadeInAnimation;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mIsAnimating = false;
        mBackImageView = (ImageView) findViewById(R.id.imageView1);
        mAnswerLabel = (TextView) findViewById(R.id.textView1);
        mAnswerButton = (Button) findViewById(R.id.button1); 
        mPlayer = MediaPlayer.create(this, R.raw.crystal_ball);
        mAnswerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				animateCrystallBall();
			}
		});
    	mBackImageView.setImageResource(R.drawable.ball_animation);
    	mBallAnimation = (AnimationDrawable) mBackImageView.getDrawable();
    	mFadeInAnimation = new AlphaAnimation(0, 1);
    	mFadeInAnimation.setDuration(1500);
    	mFadeInAnimation.setFillAfter(true);
    	mFadeInAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				mIsAnimating = true;
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				mIsAnimating = false;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}
    	});
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new OnShakeListener() {
			@Override
			public void onShake() {
				animateCrystallBall();
			}
		});
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	mSensorManager.unregisterListener(mShakeDetector);
    }
    
    private void animateCrystallBall() {
    	if(!mIsAnimating)
		{
			mAnswerLabel.setText(CrystallBall.getAnAnswer());
    		animateBackground();
	    	animateAnswer();
	    	playSound();
		}
    }
    
    private void animateBackground()
    {
    	if(mBallAnimation.isRunning())
    	{
    		mBallAnimation.stop();
    	}
    	mBallAnimation.start();
    }
    
    private void animateAnswer() {
    	mAnswerLabel.setAnimation(mFadeInAnimation);
    }
    
    private void playSound() {
    	if(!mPlayer.isPlaying())
    	{
    		mPlayer.start();
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
