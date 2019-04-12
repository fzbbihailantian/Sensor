package com.fzb.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Timer;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {
ImageView imageView;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        imageView = findViewById(R.id.image);

        initSensor();
    }

    SensorManager sensorManager;
    Sensor sensor;
    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(this,sensor);
        super.onDestroy();
    }

    private float predegree = 0;
    @Override
    public void onSensorChanged(SensorEvent event) {
//        float[] value = event.values;
        float degree = event.values[0];
        if (predegree==degree){return;}
        RotateAnimation animation = new RotateAnimation(predegree, degree,
                Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(0);
        imageView.startAnimation(animation);
        predegree=degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
