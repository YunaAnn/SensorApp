package yunaann.sensorapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

public class PositionSensorsActivity extends AppCompatActivity  implements SensorEventListener, NavigationView.OnNavigationItemSelectedListener
{

    SensorManager sensorManager;

    Sensor gameRotationVector;
    Sensor geomagneticRotationVector;
    Sensor magneticField;
    Sensor proximity;

    float [] gamRotVec;
    float [] geoRotVec;
    float [] magFie;
    float [] pro;

    boolean gamRotVecCheck;
    boolean geoRotVecCheck;
    boolean magFieCheck;
    boolean proCheck;

    public static boolean sensingOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_sensors);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gameRotationVector = null;
        geomagneticRotationVector = null;
        magneticField = null;
        proximity = null;

        gamRotVecCheck = false;
        geoRotVecCheck = false;
        magFieCheck = false;
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
            TextView  gamRotVecView2 = (TextView) findViewById(R.id.gameRotationVector2);
            TextView  gamRotVecView3 = (TextView) findViewById(R.id.gameRotationVector3);
            gamRotVecView.setText("x * sin(θ/2) = " + gamRotVec[0]);
            gamRotVecView2.setText("y * sin(θ/2) = " + gamRotVec[1]);
            gamRotVecView3.setText("z * sin(θ/2) = " + gamRotVec[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR)
        {
            geoRotVec = event.values;
            geoRotVecCheck = true;
            TextView geoRotVecView = (TextView) findViewById(R.id.geomagneticRotationVector);
            TextView geoRotVecView2 = (TextView) findViewById(R.id.geomagneticRotationVector2);
            TextView geoRotVecView3 = (TextView) findViewById(R.id.geomagneticRotationVector3);
            geoRotVecView.setText("x * sin(θ/2) = " + geoRotVec[0]);
            geoRotVecView2.setText("y * sin(θ/2) = " + geoRotVec[1]);
            geoRotVecView3.setText("z * sin(θ/2) = " + geoRotVec[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
        {
            magFie = event.values;
            magFieCheck = true;
            TextView magFieView = (TextView) findViewById(R.id.magneticField);
            TextView magFieView2 = (TextView) findViewById(R.id.magneticField2);
            TextView magFieView3 = (TextView) findViewById(R.id.magneticField3);
            magFieView.setText("x = " + magFie[0]);
            magFieView2.setText("y = " + magFie[1]);
            magFieView3.setText("z = " + magFie[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY)
        {
            pro = event.values;
            proCheck = true;
            TextView  proView = (TextView) findViewById(R.id.proximity);
            proView.setText("PROXIMITY [cm] : " + pro[0]);
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


