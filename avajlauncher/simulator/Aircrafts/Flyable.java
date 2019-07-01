package avajlauncher.simulator.Aircrafts;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}