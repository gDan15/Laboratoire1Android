package com.example.gaetan.firstapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.io.IOException;

import static com.example.gaetan.firstapplication.NetworkUtils.getResponseFromHttpUrl;


public class FirstApplication extends AppCompatActivity {

    private TextView mDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevision);

        TextView city = (TextView) findViewById(R.id.city);
        TextView country = (TextView) findViewById(R.id.country);
        TextView temperatureDay = (TextView) findViewById(R.id.temperatureDay);

        Intent intent = getIntent();
        Prevision prevision = Prevision.find(intent.getIntExtra(Intent.EXTRA_INDEX, 0));
//
        city.setText(prevision.getCity());
        country.setText(prevision.getCountry());
        temperatureDay.setText(prevision.getTemperatureDay());
    }
}
