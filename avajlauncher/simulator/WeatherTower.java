package avajlauncher.simulator;

import avajlauncher.weather.WeatherProvider;
import avajlauncher.weather.Coordinates;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates){
        return WeatherTower.getProvider().getCurrentWeather(coordinates);
    }
    void changeWeather(){
        this.conditionChanged();
    }
}