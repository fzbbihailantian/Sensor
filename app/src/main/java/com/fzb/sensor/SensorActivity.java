package com.fzb.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SensorActivity extends AppCompatActivity implements SensorEventListener{

    TextView tv_sensor;

    SensorManager manager;
    Sensor sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        initView();
        initData();
    }

    private void initData() {
        if (MyAppLication.sensor==null)return;
        setTitle(getSensorname(MyAppLication.sensor));
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(MyAppLication.sensor.getType());
         manager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_UI);
//        Toast.makeText(this,b?"开始获取":"开启失败",Toast.LENGTH_SHORT).show();


    }

    private void initView() {
        tv_sensor = findViewById(R.id.tv_sensor);
    }

    @Override
    protected void onDestroy() {
        manager.unregisterListener(this,sensor);
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] data = event.values;
        if (data!=null){
            tv_sensor.setText("");
            for (float datas :
                    data) {
                tv_sensor.append("----------"+datas+"\n");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    private String getSensorname(Sensor sensor) {
        String name = "其他";

        switch (sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                name =" 加速度传感器(Accelerometer sensor)" ;
                break;
            case Sensor.TYPE_GYROSCOPE:
                name =" 陀螺仪传感器(Gyroscope sensor)" ;
                break;
            case Sensor.TYPE_LIGHT:
                name =" 光线传感器(Light sensor)" ;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                name =" 磁场传感器(Magnetic field sensor)" ;
                break;
            case Sensor.TYPE_ORIENTATION:
                name =" 方向传感器(Orientation sensor)" ;
                break;
            case Sensor.TYPE_PRESSURE:
                name =" 气压传感器(Pressure sensor)" ;
                break;
            case Sensor.TYPE_PROXIMITY:
                name =" 距离传感器(Proximity sensor)" ;
                break;
            case Sensor.TYPE_TEMPERATURE:
                name =" 温度传感器(Temperature sensor)" ;
                break;
                case Sensor.TYPE_ROTATION_VECTOR:
                name ="旋转矢量传感器(Otation_vector sensor)" ;
                break;
            default:
                name =" 其他传感器" ;
                break;
        }
        return name;
    }

}
