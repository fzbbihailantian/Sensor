# Sensor
android 硬件传感器

MainActivity

SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    List<Sensor>    list = sm.getSensorList(Sensor.TYPE_ALL);  获取所有可以用传感器
