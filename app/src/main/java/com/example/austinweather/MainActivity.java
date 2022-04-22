package com.example.austinweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private TextView main_info;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_info = findViewById(R.id.tempC);
        button = findViewById(R.id.button);
        button.setOnClickListener(view -> new fetchWeatherData().execute());
    }


    private class fetchWeatherData extends AsyncTask<String, String, WeatherData> {

        protected void onPreExecute() {
            super.onPreExecute();
            main_info.setText("Fetching Weather");
        }

        @Override
        protected WeatherData doInBackground(String... strings) {
            try {
                URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=30.267153&lon=-97.743057&exclude=minutely&appid=25a117ed164148fa411a7c4348327156");
                InputStreamReader reader = new InputStreamReader(url.openStream());
                WeatherData weatherData = new Gson().fromJson(reader, WeatherData.class);
                return weatherData;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(WeatherData weatherData) {
            main_info.setText(weatherData.toString());
        }

    }
}