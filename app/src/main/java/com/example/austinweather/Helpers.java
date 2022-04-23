package com.example.austinweather;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Helpers {

    public class CityGeolocation {
        private String name;
        private double lat;
        private double lon;
    }

    public static WeatherData WeatherOneCallAPI(String city, String APIkey){
        try {
            CityGeolocation cityGeolocation = cityGeolocationAPIcall(city, APIkey);
            URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=" + cityGeolocation.lat + "&lon=" + cityGeolocation.lon + "&exclude=minutely&appid=" + APIkey);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            WeatherData weatherData = new Gson().fromJson(reader, WeatherData.class);
            return weatherData;
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CityGeolocation cityGeolocationAPIcall(String cityName, String APIkey){
        try {
            URL url = new URL("https://api.openweathermap.org/geo/1.0/direct?q=" + cityName + "&limit=1&appid="+ APIkey);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            CityGeolocation[] cityGeolocation = new Gson().fromJson(reader, CityGeolocation[].class);
            return cityGeolocation[0];
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
