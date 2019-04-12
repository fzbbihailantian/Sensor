package com.fzb.sensor;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    BaseAdapter adapter;
     List<Sensor> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {

        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        list = sm.getSensorList(Sensor.TYPE_ALL);
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder viewHolder = null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item, parent, false);
                    viewHolder = new ViewHolder();
                    viewHolder.textView = convertView.findViewById(R.id.text);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                Sensor sensor = list.get(position);
                String snmae = getSensorname(sensor);
                viewHolder.textView.setText("设备名称" + sensor.getName() + "-版本" + sensor.getVersion() + "供应商" + sensor.getVendor() + "\n" + snmae);
                return convertView;
            }
        };
        listView.setAdapter(adapter);
        textView.setText("手机有" + list.size() + "个传感器");
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
            default:
              name =" 其他传感器" ;
                break;
        }
        return name;
    }

    private void initView() {
        listView = findViewById(R.id.list);
        textView = findViewById(R.id.textinfo);
        listView.setOnItemClickListener(( parent, view,  position, id)->{
            Sensor sensor = list.get(position);
            MyAppLication.sensor = sensor;
            Intent intent = new Intent(MainActivity.this, SensorActivity.class);
            startActivity(intent);
        });
    }

    public class ViewHolder {
        TextView textView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0, CompassActivity.class.getSimpleName());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                startActivity(new Intent(this, CompassActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
