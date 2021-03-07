package com.example.pelota;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView Equipo1, Equipo2, mEquipo1, mEquipo2;
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
    }
}