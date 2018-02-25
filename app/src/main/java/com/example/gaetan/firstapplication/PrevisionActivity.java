package com.example.gaetan.firstapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gaetan.firstapplication.Prevision;

public class PrevisionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        TextView city = (TextView) findViewById(R.id.city);
        TextView country = (TextView) findViewById(R.id.country);
        TextView temperatureDay = (TextView) findViewById(R.id.temperatureDay);

        Intent intent = getIntent();
        /*
        * TODO : problem over here. Doesn't seem to "complete" the array
        * */
        Prevision prevision = Prevision.find(intent.getIntExtra(Intent.EXTRA_INDEX, 0));

        city.setText(prevision.getCity());
        country.setText(prevision.getCountry());
        temperatureDay.setText(prevision.getTemperatureDay());
    }
}
