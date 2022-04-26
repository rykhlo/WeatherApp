package com.example.austinweather;

import android.os.Build;
import android.widget.Toast;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Helpers {

    private static final DecimalFormat df = new DecimalFormat("0");

    public class CityGeolocation {
        private String name;
        private String state;
        private double lat;
        private double lon;
    }

    public static WeatherData WeatherOneCallAPI(String city, String APIkey){
        try {
            CityGeolocation cityGeolocation = cityGeolocationAPIcall(city, APIkey);
            URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=" + cityGeolocation.lat + "&lon=" + cityGeolocation.lon + "&exclude=minutely&appid=" + APIkey);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            WeatherData weatherData = new Gson().fromJson(reader, WeatherData.class);
            weatherData.setCity(cityGeolocation.name);
            weatherData.setState(cityGeolocation.state);
            return weatherData;
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return null;
    }

    public static CityGeolocation cityGeolocationAPIcall(String cityName, String APIkey){
        try {
            //https://openweathermap.org/api/geocoding-api
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

    public static String kelvinToF(Double K){
        Double tempF = 1.8*(K-273) + 32;
        return df.format(tempF) + "°F";
    }

    public static String kelvinToC(Double K){
        Double tempC = K-273.15;
        return df.format(tempC) + "°C";
    }

    public static String getTimeFromUTC(Long dt, String timezone){
        Date date = new Date(dt * 1000);
        DateFormat format = new SimpleDateFormat("hh:mm aa");
        format.setTimeZone(TimeZone.getTimeZone(timezone));
        String formatted = format.format(date);
        return formatted;
    }
    public static String getTimeFromUTCDayName(Long dt, String timezone){
        Date date = new Date(dt * 1000);
        DateFormat format = new SimpleDateFormat("EEE");
        format.setTimeZone(TimeZone.getTimeZone(timezone));
        String formatted = format.format(date);
        return formatted;
    }

}
