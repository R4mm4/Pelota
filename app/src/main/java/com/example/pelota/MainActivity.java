package com.example.pelota;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView Equipo1;
    TextView Equipo2;
    TextView mEquipo1;
    TextView mEquipo2;
    ImageView bola;

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    int ancho = 0, alto = 0, PEquipo1 = 0, PEquipo2 = 0;
    DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Equipo1 = findViewById(R.id.lblEquipo1);
        Equipo2 = findViewById(R.id.lblEquipo2);
        mEquipo1 = findViewById(R.id.lblMarcadorRealMadrid);
        mEquipo2 = findViewById(R.id.lblMarcadorBarcelona);
        bola = findViewById(R.id.imgBalon);
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ancho = metrics.widthPixels;
        alto = metrics.heightPixels;
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor == null) {
            Toast.makeText(
                    this,
                    "Pues no jala",
                    Toast.LENGTH_LONG
            ).show();
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                if (x < (-1)) {
                    if (bola.getX() < (ancho - bola.getWidth())) {
                        bola.setX(bola.getX() + 5);
                    }
                } else if (x > 1) {
                    if (bola.getX() > 1) {
                        bola.setX(bola.getX() - 5);
                    }
                }

                if (y < (-1)) {
                    if (bola.getY() > 0) {
                        bola.setY(bola.getY() - 5);
                    } else {
                        if ((bola.getX() > 400) && (bola.getX() < 580)) {
                            punto();
                            PEquipo2++;
                            mEquipo2.setText(String.valueOf(PEquipo2));
                        }
                    }
                } else if (y > 1) {
                    if (bola.getY() < ((ancho - bola.getHeight()) + 625)) {
                        bola.setY(bola.getY() + 5);
                    } else {
                        if ((bola.getX() > 400) && (bola.getX() < 580)) {
                            punto();
                            PEquipo1++;
                            mEquipo1.setText(String.valueOf(PEquipo1));
                        }
                    }
                }
                if (z < (-1)) {
                    bola.setMaxWidth(100);
                    bola.setMaxHeight(100);
                } else if (z > 1) {
                    bola.setMaxWidth(100);
                    bola.setMaxHeight(100);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        sensorManager.registerListener(
                sensorEventListener,
                sensor,
                sensorManager.SENSOR_DELAY_FASTEST
        );
    }
}