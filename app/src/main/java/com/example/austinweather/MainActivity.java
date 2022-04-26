package com.example.austinweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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
    private TextView stateTextView;
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

    private TextView dailyTextView1;
    private TextView dailyTextView2;
    private TextView dailyTextView3;
    private TextView dailyTextView4;
    private TextView dailyTextView5;
    private TextView dailyTextView6;
    private TextView dailyTextView7;

    private TextView dailyTempTextView1;
    private TextView dailyTempTextView2;
    private TextView dailyTempTextView3;
    private TextView dailyTempTextView4;
    private TextView dailyTempTextView5;
    private TextView dailyTempTextView6;
    private TextView dailyTempTextView7;

    private ImageView dailyImageView1;
    private ImageView dailyImageView2;
    private ImageView dailyImageView3;
    private ImageView dailyImageView4;
    private ImageView dailyImageView5;
    private ImageView dailyImageView6;
    private ImageView dailyImageView7;

    private ConstraintLayout mainConstaintLayout;
    private ImageView brynImageView;
    private EditText introEditText;



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
        stateTextView = findViewById(R.id.stateTextView);
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
        dailyTextView1 = findViewById(R.id.dailyTextView1);
        dailyTextView2 = findViewById(R.id.dailyTextView2);
        dailyTextView3 = findViewById(R.id.dailyTextView3);
        dailyTextView4 = findViewById(R.id.dailyTextView4);
        dailyTextView5 = findViewById(R.id.dailyTextView5);
        dailyTextView6 = findViewById(R.id.dailyTextView6);
        dailyTextView7 = findViewById(R.id.dailyTextView7);
        dailyTempTextView1 = findViewById(R.id.dailyTempTextView1);
        dailyTempTextView2 = findViewById(R.id.dailyTempTextView2);
        dailyTempTextView3 = findViewById(R.id.dailyTempTextView3);
        dailyTempTextView4 = findViewById(R.id.dailyTempTextView4);
        dailyTempTextView5 = findViewById(R.id.dailyTempTextView5);
        dailyTempTextView6 = findViewById(R.id.dailyTempTextView6);
        dailyTempTextView7 = findViewById(R.id.dailyTempTextView7);
        dailyImageView1 = findViewById(R.id.dailyImageView1);
        dailyImageView2 = findViewById(R.id.dailyImageView2);
        dailyImageView3 = findViewById(R.id.dailyImageView3);
        dailyImageView4 = findViewById(R.id.dailyImageView4);
        dailyImageView5 = findViewById(R.id.dailyImageView5);
        dailyImageView6 = findViewById(R.id.dailyImageView6);
        dailyImageView7 = findViewById(R.id.dailyImageView7);
        mainConstaintLayout = findViewById(R.id.mainConstraintLayout);
        brynImageView = findViewById(R.id.brynImageView);
        introEditText = findViewById(R.id.intoEditText);

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
                stateTextView.setText(weatherData.getState());

                weatherIconImageView.setImageResource(weatherIconImageView.getContext().getResources().getIdentifier(weatherData.getCurrentIcon(), "drawable", weatherIconImageView.getContext().getPackageName()));

                hourlyTimeTextView1.setText(Helpers.getTimeFromUTC(weatherData.getHourlyTimeStamp(1), weatherData.getTimezone()));
                hourlyTimeTextView2.setText(Helpers.getTimeFromUTC(weatherData.getHourlyTimeStamp(2), weatherData.getTimezone()));
                hourlyTimeTextView3.setText(Helpers.getTimeFromUTC(weatherData.getHourlyTimeStamp(3), weatherData.getTimezone()));
                hourlyTimeTextView4.setText(Helpers.getTimeFromUTC(weatherData.getHourlyTimeStamp(4), weatherData.getTimezone()));
                hourlyTimeTextView5.setText(Helpers.getTimeFromUTC(weatherData.getHourlyTimeStamp(5), weatherData.getTimezone()));

                hourlyTempTextView1.setText(Helpers.kelvinToF(weatherData.getHourlyTemp(1)));
                hourlyTempTextView2.setText(Helpers.kelvinToF(weatherData.getHourlyTemp(2)));
                hourlyTempTextView3.setText(Helpers.kelvinToF(weatherData.getHourlyTemp(3)));
                hourlyTempTextView4.setText(Helpers.kelvinToF(weatherData.getHourlyTemp(4)));
                hourlyTempTextView5.setText(Helpers.kelvinToF(weatherData.getHourlyTemp(5)));

                hourlyIconImageView1.setImageResource(hourlyIconImageView1.getContext().getResources().getIdentifier(weatherData.getHourlyIcon(1), "drawable", hourlyIconImageView1.getContext().getPackageName()));
                hourlyIconImageView2.setImageResource(hourlyIconImageView2.getContext().getResources().getIdentifier(weatherData.getHourlyIcon(2), "drawable", hourlyIconImageView2.getContext().getPackageName()));
                hourlyIconImageView3.setImageResource(hourlyIconImageView3.getContext().getResources().getIdentifier(weatherData.getHourlyIcon(3), "drawable", hourlyIconImageView3.getContext().getPackageName()));
                hourlyIconImageView4.setImageResource(hourlyIconImageView4.getContext().getResources().getIdentifier(weatherData.getHourlyIcon(4), "drawable", hourlyIconImageView4.getContext().getPackageName()));
                hourlyIconImageView5.setImageResource(hourlyIconImageView5.getContext().getResources().getIdentifier(weatherData.getHourlyIcon(5), "drawable", hourlyIconImageView5.getContext().getPackageName()));

                dailyTextView1.setText("Today");
                dailyTextView2.setText(Helpers.getTimeFromUTCDayName(weatherData.getDailyTimeStamp(1), weatherData.getTimezone()));
                dailyTextView3.setText(Helpers.getTimeFromUTCDayName(weatherData.getDailyTimeStamp(2), weatherData.getTimezone()));
                dailyTextView4.setText(Helpers.getTimeFromUTCDayName(weatherData.getDailyTimeStamp(3), weatherData.getTimezone()));
                dailyTextView5.setText(Helpers.getTimeFromUTCDayName(weatherData.getDailyTimeStamp(4), weatherData.getTimezone()));
                dailyTextView6.setText(Helpers.getTimeFromUTCDayName(weatherData.getDailyTimeStamp(5), weatherData.getTimezone()));
                dailyTextView7.setText(Helpers.getTimeFromUTCDayName(weatherData.getDailyTimeStamp(6), weatherData.getTimezone()));

                dailyTempTextView1.setText(weatherData.getDailyMinMaxTemp(0));
                dailyTempTextView2.setText(weatherData.getDailyMinMaxTemp(1));
                dailyTempTextView3.setText(weatherData.getDailyMinMaxTemp(2));
                dailyTempTextView4.setText(weatherData.getDailyMinMaxTemp(3));
                dailyTempTextView5.setText(weatherData.getDailyMinMaxTemp(4));
                dailyTempTextView6.setText(weatherData.getDailyMinMaxTemp(5));
                dailyTempTextView7.setText(weatherData.getDailyMinMaxTemp(6));

                dailyImageView1.setImageResource(dailyImageView1.getContext().getResources().getIdentifier(weatherData.getDailyIcon(0), "drawable", dailyImageView1.getContext().getPackageName()));
                dailyImageView2.setImageResource(dailyImageView2.getContext().getResources().getIdentifier(weatherData.getDailyIcon(1), "drawable", dailyImageView2.getContext().getPackageName()));
                dailyImageView3.setImageResource(dailyImageView3.getContext().getResources().getIdentifier(weatherData.getDailyIcon(2), "drawable", dailyImageView3.getContext().getPackageName()));
                dailyImageView4.setImageResource(dailyImageView4.getContext().getResources().getIdentifier(weatherData.getDailyIcon(3), "drawable", dailyImageView4.getContext().getPackageName()));
                dailyImageView5.setImageResource(dailyImageView5.getContext().getResources().getIdentifier(weatherData.getDailyIcon(4), "drawable", dailyImageView5.getContext().getPackageName()));
                dailyImageView6.setImageResource(dailyImageView6.getContext().getResources().getIdentifier(weatherData.getDailyIcon(5), "drawable", dailyImageView6.getContext().getPackageName()));
                dailyImageView7.setImageResource(dailyImageView7.getContext().getResources().getIdentifier(weatherData.getDailyIcon(6), "drawable", dailyImageView7.getContext().getPackageName()));

                mainConstaintLayout.setVisibility(View.VISIBLE);
                brynImageView.setVisibility(View.GONE);
                introEditText.setVisibility(View.GONE);

            }
            catch (NullPointerException e){
                Toast.makeText(MainActivity.this, "Please enter valid city", Toast.LENGTH_LONG).show();
            }
        }

    }
}