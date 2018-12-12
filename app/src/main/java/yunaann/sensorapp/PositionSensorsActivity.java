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

public class PositionSensorsActivity extends AppCompatActivity implements SensorEventListener
{

    SensorManager sensorManager;

    Sensor gameRotationVector;
    Sensor geomagneticRotationVector;
    Sensor magneticField;
    Sensor magneticFieldUncalibrated;
    Sensor proximity;

    float [] gamRotVec;
    float [] geoRotVec;
    float [] magFie;
    float [] magFieUnc;
    float [] pro;

    boolean gamRotVecCheck;
    boolean geoRotVecCheck;
    boolean magFieCheck;
    boolean magFieUncCheck;
    boolean proCheck;

    public static boolean sensingOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_sensors);

        gameRotationVector = null;
        geomagneticRotationVector = null;
        magneticField = null;
        magneticFieldUncalibrated = null;
        proximity = null;

        gamRotVecCheck = false;
        geoRotVecCheck = false;
        magFieCheck = false;
        magFieUncCheck = false;
        proCheck = false;
    }

    public void startSensing (View view)
    {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        this.gameRotationVector = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        if (gameRotationVector!=null)
        {
            sensorManager.registerListener(this, gameRotationVector, SensorManager.SENSOR_DELAY_GAME);
        }

        this.geomagneticRotationVector = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        if (geomagneticRotationVector!=null)
        {
            sensorManager.registerListener(this, gameRotationVector, SensorManager.SENSOR_DELAY_GAME);
        }

        this.magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticField!=null)
        {
            sensorManager.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_GAME);
        }

        this.magneticFieldUncalibrated = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
        if (magneticFieldUncalibrated!=null)
        {
            sensorManager.registerListener(this, magneticFieldUncalibrated, SensorManager.SENSOR_DELAY_GAME);
        }

        this.proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (proximity!=null)
        {
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_GAME);
        }

    }

    public void stopSensing (View view)
    {
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR));
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR));
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD));
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED));
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY));
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
        if (event.sensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR)
        {
            gamRotVec = event.values;
            gamRotVecCheck = true;
            TextView  gamRotVecView = (TextView) findViewById(R.id.gameRotationVector);
            gamRotVecView.setText("GAME ROT. VEC. : " + gamRotVec[0] + " " + gamRotVec[1] + " " + gamRotVec[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR)
        {
            geoRotVec = event.values;
            geoRotVecCheck = true;
            TextView geoRotVecView = (TextView) findViewById(R.id.geomagneticRotationVector);
            geoRotVecView.setText("GEOMAGNETIC ROT. VEC. : " + geoRotVec[0] + " " + geoRotVec[1] + " " + geoRotVec[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
        {
            magFie = event.values;
            magFieCheck = true;
            TextView magFieView = (TextView) findViewById(R.id.magneticField);
            magFieView.setText("MAGNETIC FIELD [μT] : " + Math.round(magFie[0]) + " " + Math.round(magFie[1]) + " " + Math.round(magFie[2]) + " ");

        }

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED)
        {
            magFieUnc = event.values;
            magFieUncCheck = true;
            TextView magFieUncView = (TextView) findViewById(R.id.magneticFieldUncalibrated);
            magFieUncView.setText("MAGNETIC FIELD UNC.[μT] : " + Math.round(magFieUnc[0]) + " " + Math.round(magFieUnc[1]) + " " + Math.round(magFieUnc[2]) + " " + Math.round(magFieUnc[3]) + " " + Math.round(magFieUnc[4]) + " " + Math.round(magFieUnc[5]));
        }

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY)
        {
            pro = event.values;
            proCheck = true;
            TextView  proView = (TextView) findViewById(R.id.proximity);
            proView.setText("PROXIMITY [cm] : " + pro[0] + " ");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    public void goToMotionSensors(View view)
    {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void goToEnvironmentSensors(View view)
    {
        startActivity(new Intent(getApplicationContext(), EnvironmentSensorsActivity.class));
    }

}


