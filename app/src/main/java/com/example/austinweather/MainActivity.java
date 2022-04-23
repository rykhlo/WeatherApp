package com.example.austinweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private String APIkey = "25a117ed164148fa411a7c4348327156";
    private TextView cityStateTextView;
    private TextView tempCTextView;
    private TextView tempFTextView;
    private TextView currentTimeTextView;
    private TextView weatherDescriptionTextView;
    private TextView humidityTextView;
    private TextView windSpeedTextView;
    private TextView pressureTextView;
    private Button citySearchButton;
    private ImageView weatherIconImageView;
    private AutoCompleteTextView citySearchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        citySearchButton = findViewById(R.id.citySearchButton);
        citySearchField = findViewById(R.id.citySearchField);
        cityStateTextView = findViewById(R.id.cityStateTextView);
        tempCTextView = findViewById(R.id.tempCTextView);
        tempFTextView = findViewById(R.id.tempFTextView);
        humidityTextView = findViewById(R.id.humidityTextView);
        windSpeedTextView = findViewById(R.id.windSpeedTextView);
        pressureTextView = findViewById(R.id.pressureTextView);
        weatherDescriptionTextView = findViewById(R.id.weatherDescriptionTextView);
        weatherIconImageView = findViewById(R.id.weatherIconImageView);
        currentTimeTextView = findViewById(R.id.currentTimeTextView);
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

        @Override
        protected WeatherData doInBackground(String... strings) {
            return Helpers.WeatherOneCallAPI(strings[0],APIkey);
        }

        @Override
        protected void onPostExecute(WeatherData weatherData) {
            try{
                cityStateTextView.setText(weatherData.getCity());
                tempFTextView.setText(Helpers.kelvinToF(weatherData.getCurrentTemp()));
                tempCTextView.setText(Helpers.kelvinToC(weatherData.getCurrentTemp()));
                currentTimeTextView.setText(Helpers.getTimeFromUTC(weatherData.getCurrentTimeStamp(), weatherData.getTimezone()));
                weatherDescriptionTextView.setText(weatherData.getCurrentWeatherDescription());
                humidityTextView.setText(weatherData.getCurrentHumidity());
                windSpeedTextView.setText(weatherData.getCurrentWindSpeed());
                pressureTextView.setText(weatherData.getCurrentPressure());
                Context context = weatherIconImageView.getContext();
                int id = context.getResources().getIdentifier(weatherData.getCurrentIcon(), "drawable", context.getPackageName());
                weatherIconImageView.setImageResource(id);

            }
            catch (NullPointerException e){
                Toast.makeText(MainActivity.this, "Please enter valid city", Toast.LENGTH_LONG).show();
            }
        }

    }
}