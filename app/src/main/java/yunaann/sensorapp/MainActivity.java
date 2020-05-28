package yunaann.sensorapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;;

public class MainActivity extends AppCompatActivity implements SensorEventListener, NavigationView.OnNavigationItemSelectedListener
{
    SensorManager sensorManager;

    Sensor accelerometer;
    Sensor gravity;
    Sensor gyroscope;
    Sensor linearAcceleration;
    Sensor rotationVector;

    float [] acc;
    float [] gra;
    float [] gyr;
    float [] linAcc;
    float [] rotVec;

    boolean accCheck;
    boolean graCheck;
    boolean gyrCheck;
    boolean linAccCheck;
    boolean rotVecCheck;

    public static boolean sensingOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        accelerometer = null;
        gravity = null;
        gyroscope = null;
        linearAcceleration = null;
        rotationVector = null;

        accCheck = false;
        graCheck = false;
        gyrCheck = false;
        linAccCheck = false;
        rotVecCheck = false;
    }

    public void startSensing (View view)
    {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        this.accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer!=null)
        {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
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
    }

    public void stopSensing (View view)
    {
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY));
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION));
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR));
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
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            acc = event.values;
            accCheck = true;
            TextView accelView = (TextView) findViewById(R.id.accelerometer);
            TextView accelView2 = (TextView) findViewById(R.id.accelerometer2);
            TextView accelView3 = (TextView) findViewById(R.id.accelerometer3);
            accelView.setText("x = " + acc[0]);
            accelView2.setText("y = " + acc[1]);
            accelView3.setText("z = " + acc[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_GRAVITY)
        {
            gra = event.values;
            graCheck = true;
            TextView graView = (TextView) findViewById(R.id.gravity);
            TextView graView2 = (TextView) findViewById(R.id.gravity2);
            TextView graView3 = (TextView) findViewById(R.id.gravity3);
            graView.setText("x = " + gra[0]);
            graView2.setText("y = " + gra[1]);
            graView3.setText("z = " + gra[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            gyr = event.values;
            gyrCheck = true;
            TextView gyroView = (TextView) findViewById(R.id.gyroscope);
            TextView gyroView2 = (TextView) findViewById(R.id.gyroscope2);
            TextView gyroView3 = (TextView) findViewById(R.id.gyroscope3);
            gyroView.setText("x = " + gyr[0]);
            gyroView2.setText("y = " + gyr[1]);
            gyroView3.setText("z = " + gyr[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION)
        {
            linAcc = event.values;
            linAccCheck = true;
            TextView linAccView = (TextView) findViewById(R.id.linearAcceleration);
            TextView linAccView2 = (TextView) findViewById(R.id.linearAcceleration2);
            TextView linAccView3 = (TextView) findViewById(R.id.linearAcceleration3);
            linAccView.setText("x = " + linAcc[0]);
            linAccView2.setText("y = " + linAcc[1]);
            linAccView3.setText("z = " + linAcc[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR)
        {
            rotVec = event.values;
            rotVecCheck = true;
            TextView rotVecView = (TextView) findViewById(R.id.rotationVector);
            TextView rotVecView2 = (TextView) findViewById(R.id.rotationVector2);
            TextView rotVecView3 = (TextView) findViewById(R.id.rotationVector3);
            TextView rotVecView4 = (TextView) findViewById(R.id.rotationVector4);
            rotVecView.setText("x * sin(θ/2) = " + rotVec[0]);
            rotVecView2.setText("y * sin(θ/2) = " + rotVec[1]);
            rotVecView3.setText("z * sin(θ/2) = " + rotVec[2]);
            rotVecView4.setText("cos(θ/2) = " + rotVec[3]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.nav_main)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_position_sensors)
        {
            Intent intent = new Intent(this, PositionSensorsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_environment_sensors)
        {
            Intent intent = new Intent(this, EnvironmentSensorsActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


