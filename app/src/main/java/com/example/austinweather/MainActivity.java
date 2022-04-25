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

    private TextView hourlyTimeTextView1;
    private TextView hourlyTimeTextView2;
    private TextView hourlyTimeTextView3;
    private TextView hourlyTimeTextView4;
    private TextView hourlyTimeTextView5;
    private TextView hourlyTempTextView1;
    private TextView hourlyTempTextView2;
    private TextView hourlyTempTextView3;
    private TextView hourlyTempTextView4;
    private TextView hourlyTempTextView5;
    private ImageView hourlyIconImageView1;
    private ImageView hourlyIconImageView2;
    private ImageView hourlyIconImageView3;
    private ImageView hourlyIconImageView4;
    private ImageView hourlyIconImageView5;

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
        hourlyTimeTextView1 = findViewById(R.id.hourlyTimeTextView1);
        hourlyTimeTextView2 = findViewById(R.id.hourlyTimeTextView2);
        hourlyTimeTextView3 = findViewById(R.id.hourlyTimeTextView3);
        hourlyTimeTextView4 = findViewById(R.id.hourlyTimeTextView4);
        hourlyTimeTextView5 = findViewById(R.id.hourlyTimeTextView5);
        hourlyTempTextView1 = findViewById(R.id.hourlyTempTextView1);
        hourlyTempTextView2 = findViewById(R.id.hourlyTempTextView2);
        hourlyTempTextView3 = findViewById(R.id.hourlyTempTextView3);
        hourlyTempTextView4 = findViewById(R.id.hourlyTempTextView4);
        hourlyTempTextView5 = findViewById(R.id.hourlyTempTextView5);
        hourlyIconImageView1 = findViewById(R.id.hourlyIconImageView1);
        hourlyIconImageView2 = findViewById(R.id.hourlyIconImageView2);
        hourlyIconImageView3 = findViewById(R.id.hourlyIconImageView3);
        hourlyIconImageView4 = findViewById(R.id.hourlyIconImageView4);
        hourlyIconImageView5 = findViewById(R.id.hourlyIconImageView5);
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

                weatherIconImageView.setImageResource(weatherIconImageView.getContext().getResources().getIdentifier(weatherData.getCurrentIcon(), "drawable", weatherIconImageView.getContext().getPackageName()));

                hourlyTimeTextView1.setText(Helpers.getTimeFromUTC(weatherData.getHourlyTimeStamp(1), weatherData.getTimezone()));
                hourlyTimeTextView2.setText(Helpers.getTimeFromUTC(weatherData.getHourlyTimeStamp(2), weatherData.getTimezone()));
                hourlyTimeTextView3.setText(Helpers.getTimeFromUTC(weatherData.getHourlyTimeStamp(3), weatherData.getTimezone()));
                hourlyTimeTextView4.setText(Helpers.getTimeFromUTC(weatherData.getHourlyTimeStamp(4), weatherData.getTimezone()));
                hourlyTimeTextView5.setText(Helpers.getTimeFromUTC(weatherData.getHourlyTimeStamp(5), weatherData.getTimezone()));

                hourlyTempTextView1.setText(Helpers.kelvinToF(weatherData.getHourlyTemp(1)));
                hourlyTempTextView1.setText(Helpers.kelvinToF(weatherData.getHourlyTemp(2)));
                hourlyTempTextView1.setText(Helpers.kelvinToF(weatherData.getHourlyTemp(3)));
                hourlyTempTextView1.setText(Helpers.kelvinToF(weatherData.getHourlyTemp(4)));
                hourlyTempTextView1.setText(Helpers.kelvinToF(weatherData.getHourlyTemp(5)));

                hourlyIconImageView1.setImageResource(hourlyIconImageView1.getContext().getResources().getIdentifier(weatherData.getHourlyIcon(1), "drawable", hourlyIconImageView1.getContext().getPackageName()));
                hourlyIconImageView2.setImageResource(hourlyIconImageView2.getContext().getResources().getIdentifier(weatherData.getHourlyIcon(2), "drawable", hourlyIconImageView2.getContext().getPackageName()));
                hourlyIconImageView3.setImageResource(hourlyIconImageView3.getContext().getResources().getIdentifier(weatherData.getHourlyIcon(3), "drawable", hourlyIconImageView3.getContext().getPackageName()));
                hourlyIconImageView4.setImageResource(hourlyIconImageView4.getContext().getResources().getIdentifier(weatherData.getHourlyIcon(4), "drawable", hourlyIconImageView4.getContext().getPackageName()));
                hourlyIconImageView5.setImageResource(hourlyIconImageView5.getContext().getResources().getIdentifier(weatherData.getHourlyIcon(5), "drawable", hourlyIconImageView5.getContext().getPackageName()));




            }
            catch (NullPointerException e){
                Toast.makeText(MainActivity.this, "Please enter valid city", Toast.LENGTH_LONG).show();
            }
        }

    }
}