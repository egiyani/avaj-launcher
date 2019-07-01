package avajlauncher.simulator.Aircrafts;
import avajlauncher.weather.Coordinates;

public class Aircraft {

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;
    private long nextId(){
        return ++(Aircraft.idCounter);
    }

    protected Aircraft(String name, Coordinates coordinates){
        this.id = this.nextId();
        this.name = name;
        this.coordinates = coordinates;
    }
}