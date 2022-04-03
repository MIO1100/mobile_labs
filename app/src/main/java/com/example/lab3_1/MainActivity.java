package com.example.lab3_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class MainActivity extends AppCompatActivity {
    EditText city;
    Button place, sec, weather, stop;
    TextView out;

    private int seconds;
    private boolean sec_status;
    private LocationManager locationManager;

    StringBuilder sbGPS = new StringBuilder();
    StringBuilder sbNet = new StringBuilder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.et_city);
        place = findViewById(R.id.btn_place);
        sec = findViewById(R.id.btn_sec);
        weather = findViewById(R.id.btn_weather);
        stop = findViewById(R.id.btn_stop);
        out = findViewById(R.id.text_out);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out.setText("");
                if (city.getText().toString().equals("Barnaul")) {
                    out.setText("Погода в Барнауле -5");
                } else {
                    if (city.getText().toString().equals("Novosibirsk")) {
                        out.setText("Погода в Новосибирске +20");
                    } else {
                        out.setText("Данные не найдены");
                    }
                }

            }
        });
        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sec_status = true;
                timer();

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sec_status = false;

            }
        });
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEnabled();
                try {
                    Criteria criteria = new Criteria();

                    showLocation(getLastKnownLocation());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void timer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int second = seconds % 60;

                if (sec_status) {

                    String time = String.format("%d:%02d:%02d", hours, minutes, second);
                    out.setText(time);
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void showLocation(Location location) {
        if (location == null)
            return;
        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
            out.setText(out.getText() + formatLocation(location));
        } else if (location.getProvider().equals(
                LocationManager.NETWORK_PROVIDER)) {
            out.setText(out.getText() + formatLocation(location));
        }
    }

    private String formatLocation(Location location) {
        if (location == null)
            return "";
        return String.format(
                "\n Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT",
                location.getLatitude(), location.getLongitude(), new Date(
                        location.getTime()));
    }

    private void checkEnabled() {
        out.setText("Enabled: "
                + locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER));
        out.setText("Enabled: "
                + locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER));
    }
    private Location getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }

        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location:
                bestLocation = l;
            }
        }

        return bestLocation;
    }
}