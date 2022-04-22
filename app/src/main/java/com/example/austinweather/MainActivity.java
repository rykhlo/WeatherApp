package com.example.austinweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
        main_info = findViewById(R.id.main_info);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetJSONfromURL().execute();
            }
        });
    }

    private class WeatherData {
        String timezone;
        String temperature;
        String pressure;
        String humidity;
        String weatherState;
        String weatherDescription;

        public void parseOneCallApiJson(JsonObject json){
            JsonObject current = json.getAsJsonObject("current");
            JsonObject weather = current.getAsJsonArray("weather").get(0).getAsJsonObject();
            timezone = json.get("timezone").getAsString();
            temperature = current.get("temp").getAsString();
            pressure = current.get("pressure").getAsString();
            humidity = current.get("humidity").getAsString();
            weatherState = weather.get("main").getAsString();
            weatherDescription = weather.get("description").getAsString();
        }
        @Override
        public String toString(){
            return "timezone: " + timezone +"\ntemperature: " + temperature + "\npressure: " + pressure +"\nhumidity: " + humidity
                    + "\nweatherState: " + weatherState + "\nweatherDescription: " + weatherDescription;
        }
    }

    private class GetJSONfromURL extends AsyncTask<String, String, WeatherData> {

        protected void onPreExecute() {
            super.onPreExecute();
            main_info.setText("Fetching Weather");
        }

        @Override
        protected WeatherData doInBackground(String... strings) {
            try {
                URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=30.267153&lon=-97.743057&exclude=minutely&appid=25a117ed164148fa411a7c4348327156");
                InputStreamReader reader = new InputStreamReader(url.openStream());
                JsonObject json = new Gson().fromJson(reader, JsonObject.class);
                WeatherData weatherData = new WeatherData();
                weatherData.parseOneCallApiJson(json);
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