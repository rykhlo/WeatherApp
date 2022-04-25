package com.example.austinweather;

import java.util.List;

/** https://openweathermap.org/api/one-call-api#list1
 * Current weather
 * Minute forecast for 1 hour
 * Hourly forecast for 48 hours
 * Daily forecast for 7 days
 * National weather alerts
 * Historical weather data for the previous 5 days
 * **/

public class WeatherData {
    private String city;
    private String timezone;
    private Integer timezone_offset;
    private WeatherState current;
    private List<WeatherState> hourly;
    private List<DailyWeather> daily;

    private class WeatherInfo{
        private Integer id;
        private String main;
        private String description;
        private String icon;
    }

    private class DailyWeather{
        DayTemp temp;
        private Long dt;
        List<WeatherInfo> weather;

        private class DayTemp{
            private Double day;
            private Double min;
            private Double max;
        }
    }
    private class WeatherState{
        private Long dt;
        private Double temp;
        private Integer pressure;
        private Integer humidity;
        private Double wind_speed;
        private List<WeatherInfo> weather;
    }

    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){  return city.substring(0, 1).toUpperCase() + city.substring(1); }

    public Double getCurrentTemp() { return current.temp; }

    public Long getCurrentTimeStamp() { return current.dt; }

    public String getTimezone() { return timezone; }

    public String getCurrentWeatherDescription() { return current.weather.get(0).description; }

    public String getCurrentHumidity() { return current.humidity + "%"; }

    public String getCurrentWindSpeed() { return current.wind_speed + "m/s"; }

    public String getCurrentPressure() { return current.pressure + "hPa"; }

    public String getCurrentIcon() {
        String icon = current.weather.get(0).icon;
        return "_" + icon.substring(0, icon.length() - 1) + "d";
    }

    public Double getHourlyTemp(int index){
        return hourly.get(index).temp;
    }

    public Long getHourlyTimeStamp(int index){
        return hourly.get(index).dt;
    }

    public String getHourlyIcon(int index){
        String icon = hourly.get(index).weather.get(0).icon;
        return "_" + icon.substring(0, icon.length() - 1) + "d";
    }

    public Long getDailyTimeStamp(int index){ return daily.get(index).dt; }

    public String getDailyMinMaxTemp(int index){ return Helpers.kelvinToF(daily.get(index).temp.min) + " - " + Helpers.kelvinToF(daily.get(index).temp.max); }

    public String getDailyIcon(int index) {
        String icon = daily.get(index).weather.get(0).icon;
        return "_" + icon.substring(0, icon.length() - 1) + "d";
    }



    @Override
    public String toString(){
        return "TESTING ONLY\ntimezone: " + timezone +"\ntemperature: " + current.temp + "\npressure: " + current.pressure +"\nhumidity: " + current.humidity
                + "\nweatherState: " + current.weather.get(0).main + "\nweatherDescription: " + current.weather.get(0).description
                + "\n hourly first: " + hourly.get(0).temp + "\n daily test: " + daily.get(0).weather.get(0).main;
    }
}
