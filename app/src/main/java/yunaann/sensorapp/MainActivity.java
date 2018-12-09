package yunaann.sensorapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{

    SensorManager sensorManager;

    Sensor accelerometer;
    Sensor accelerometerUncalibrated;
    Sensor gravity;
    Sensor gyroscope;
    Sensor gyroscopeUncalibrated;
    Sensor linearAcceleration;
    Sensor rotationVector;
    Sensor stepCounter;

    float [] acc;
    float [] accUnc;
    float [] gra;
    float [] gyr;
    float [] gyrUnc;
    float [] linAcc;
    float [] rotVec;
    float [] steCou;

    boolean accCheck;
    boolean accUncCheck;
    boolean graCheck;
    boolean gyrCheck;
    boolean gyrUncCheck;
    boolean linAccCheck;
    boolean rotVecCheck;
    boolean steCouCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometer = null;
        accelerometerUncalibrated = null;
        gravity = null;
        gyroscope = null;
        gyroscopeUncalibrated = null;
        linearAcceleration = null;
        rotationVector = null;
        stepCounter = null;

        accCheck = false;
        accUncCheck = false;
        graCheck = false;
        gyrCheck = false;
        gyrUncCheck = false;
        linAccCheck = false;
        rotVecCheck = false;
        steCouCheck = false;

    }

    public void startSenMot (View view)
    {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        this.accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer!=null)
        {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }

        this.accelerometerUncalibrated = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER_UNCALIBRATED);
        if (accelerometerUncalibrated!=null)
        {
            sensorManager.registerListener(this, accelerometerUncalibrated, SensorManager.SENSOR_DELAY_GAME);
        }

        this.gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (gravity!=null)
        {
            sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_GAME);
        }

        this.gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscope!=null)
        {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_GAME);
        }

        this.gyroscopeUncalibrated = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
        if (gyroscopeUncalibrated!=null)
        {
            sensorManager.registerListener(this, gyroscopeUncalibrated, SensorManager.SENSOR_DELAY_GAME);
        }

        this.linearAcceleration = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        if (linearAcceleration!=null)
        {
            sensorManager.registerListener(this, linearAcceleration, SensorManager.SENSOR_DELAY_GAME);
        }

        this.rotationVector = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        if (rotationVector!=null)
        {
            sensorManager.registerListener(this, rotationVector, SensorManager.SENSOR_DELAY_GAME);
        }

        this.stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepCounter!=null)
        {
            sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            acc = event.values;
            accCheck = true;
            TextView accelView = (TextView) findViewById(R.id.accelerometer);
            accelView.setText("ACCELEROMETER [m/s2] : " + (acc[0]) + " " + (acc[1]) + " " + (acc[2]));
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER_UNCALIBRATED)
        {
            accUnc = event.values;
            accUncCheck = true;
            TextView accUncView = (TextView) findViewById(R.id.accelerometerUncalibrated);
            accUncView.setText("ACC. UNC.[m/s2] : " + (accUnc[0]) + " " + (accUnc[1]) + " " + (accUnc[2]) + " " + (accUnc[3]) + " " + (accUnc[4]) + " " + (accUnc[5]));
        }

        if (event.sensor.getType() == Sensor.TYPE_GRAVITY)
        {
            gra = event.values;
            graCheck = true;
            TextView graView = (TextView) findViewById(R.id.gravity);
            graView.setText("GRAVITY [m/s2] : " + Math.round(gra[0]) + " " + Math.round(gra[1]) + " " + Math.round(gra[2]) + " ");
        }

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            gyr = event.values;
            gyrCheck = true;
            TextView gyroView = (TextView) findViewById(R.id.gyroscope);
            gyroView.setText("GYROSCOPE [rad/s] : " + gyr[0] + " " + gyr[1] + " " + gyr[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE_UNCALIBRATED)
        {
            gyrUnc = event.values;
            gyrUncCheck = true;
            TextView gyroUncView = (TextView) findViewById(R.id.gyroscopeUncalibrated);
            gyroUncView.setText("GYROSCOPE UNC.[rad/s] : " + gyrUnc[0] + " " + gyrUnc[1] + " " + gyrUnc[2] + " " + gyrUnc[3] + " " + gyrUnc[4] + " " + gyrUnc[5]);
        }

        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION)
        {
            linAcc = event.values;
            linAccCheck = true;
            TextView linAccView = (TextView) findViewById(R.id.linearAcceleration);
            linAccView.setText("LINEAR ACCELERATION [m/s2] : " + Math.round(linAcc[0]) + " " + Math.round(linAcc[1]) + " " + Math.round(linAcc[2]) + " ");
        }

        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR)
        {
            rotVec = event.values;
            rotVecCheck = true;
            TextView rotVecView = (TextView) findViewById(R.id.rotationVector);
            rotVecView.setText("ROTATION VEC. : " + rotVec[0] + " " + rotVec[1] + " " + rotVec[2] + " " + rotVec[3]);
        }

        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER)
        {
            steCou = event.values;
            steCouCheck = true;
            TextView  steCouView = (TextView) findViewById(R.id.stepCounter);
            steCouView.setText("STEP COUNTER : " + steCou[0] + " ");
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

    public void goToEnvironmentSensors(View view)
    {
        startActivity(new Intent(getApplicationContext(), EnvironmentSensorsActivity.class));
    }

    }


