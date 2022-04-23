package com.example.austinweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private String APIkey = "25a117ed164148fa411a7c4348327156";
    private TextView main_info;
    private Button citySearchButton;
    private AutoCompleteTextView citySearchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_info = findViewById(R.id.tempC);
        citySearchButton = findViewById(R.id.citySearchButton);
        citySearchField = findViewById(R.id.citySearchField);
        citySearchButton.setOnClickListener(view -> {
            //display toast message if city input is invalid
            if(citySearchField.getText().toString().trim().equals("")) {
                Toast.makeText(MainActivity.this, "Please enter valid city", Toast.LENGTH_LONG).show();
            }
            else {
                String city = citySearchField.getText().toString();
                new fetchWeatherData().execute(city);
            }
        });
    }


    private class fetchWeatherData extends AsyncTask<String, String, WeatherData> {

        protected void onPreExecute() {
            super.onPreExecute();
            main_info.setText("Fetching Weather");
        }

        @Override
        protected WeatherData doInBackground(String... strings) {
            return Helpers.WeatherOneCallAPI(strings[0],APIkey);
        }

        @Override
        protected void onPostExecute(WeatherData weatherData) {
            main_info.setText(weatherData.toString());
        }

    }
}