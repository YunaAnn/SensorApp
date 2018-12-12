package yunaann.sensorapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;;

public class EnvironmentSensorsActivity extends AppCompatActivity implements SensorEventListener
{

    SensorManager sensorManager;

    Sensor ambientTemperature;
    Sensor light;
    Sensor pressure;
    Sensor relativeHumidity;

    float [] ambTem;
    float [] lig;
    float [] pre;
    float [] relHum;

    boolean ambTemCheck;
    boolean ligCheck;
    boolean preCheck;
    boolean relHumCheck;

    public static boolean sensingOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_sensors);

        ambientTemperature = null;
        light = null;
        pressure = null;
        relativeHumidity = null;

        ambTemCheck = false;
        ligCheck = false;
        preCheck = false;
        relHumCheck = false;
    }

    public void startSensing (View view)
    {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        this.ambientTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (ambientTemperature!=null)
        {
            sensorManager.registerListener(this, ambientTemperature, SensorManager.SENSOR_DELAY_GAME);
        }

        this.light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (light!=null)
        {
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_GAME);
        }

        this.pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (pressure!=null)
        {
            sensorManager.registerListener(this, pressure, SensorManager.SENSOR_DELAY_GAME);
        }

        this.relativeHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (relativeHumidity!=null)
        {
            sensorManager.registerListener(this, relativeHumidity, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public void stopSensing (View view)
    {
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE));
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT));
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE));
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY));
    }

    public void clickSen(View view)
    {
        if (sensingOn)
        {
            Button button = (Button) findViewById(R.id.sensingOn);
            button.setText("START");
            stopSensing(view);
            sensingOn = false;
        }
        else
        {
            Button button = (Button) findViewById(R.id.sensingOn);
            startSensing(view);
            button.setText("STOP");
            sensingOn = true;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE)
        {
            ambTem = event.values;
            ambTemCheck = true;
            TextView  ambTemView = (TextView) findViewById(R.id.ambientTemperature);
            ambTemView.setText("AMBIENT TEMPERATURE [°C] : " + ambTem[0] + " ");
        }

        if (event.sensor.getType() == Sensor.TYPE_LIGHT)
        {
            lig = event.values;
            ligCheck = true;
            TextView  ligView = (TextView) findViewById(R.id.light);
            ligView.setText("LIGHT [lx] : " + lig[0] + " ");
        }

        if (event.sensor.getType() == Sensor.TYPE_PRESSURE)
        {
            pre = event.values;
            preCheck = true;
            TextView  preView = (TextView) findViewById(R.id.pressure);
            preView.setText("PRESSURE [hPa] : " + pre[0] + " ");
        }

        if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY)
        {
            relHum = event.values;
            relHumCheck = true;
            TextView  relHumView = (TextView) findViewById(R.id.relativeHumidity);
            relHumView.setText("RELATIVE HUMIDITY [%] : " + relHum[0] + " ");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    public void goToPositionSensors(View view)
    {
        startActivity(new Intent(getApplicationContext(), PositionSensorsActivity.class));
    }

    public void goToMotionSensors(View view)
    {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}


