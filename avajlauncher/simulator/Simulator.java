package avajlauncher.simulator;

import avajlauncher.simulator.Aircrafts.AircraftFactory;
import avajlauncher.weather.WeatherProvider;
import java.io.*;

public class Simulator {

    public static PrintWriter writer;
    public static int cycles;

    public static void main(String[] args)
    {
        if (args.length < 1)
            return;

        String filename = args[0];

        File simulationFile = new File("simulation.txt");
        try {
            writer = new PrintWriter(simulationFile);
        } catch (FileNotFoundException fne) {
            System.out.println("Error: " + fne.getMessage());
            return;
        }
        if (simulationFile.exists() && !simulationFile.isDirectory())
            writer.print("");

        AircraftFactory aircraftFactory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();
        try {
            FileInputStream fstream = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            int line = 1;
            String[] splitted;

            while ((strLine = br.readLine()) != null)
            {
                if (line == 1)
                    try {
                        cycles = Integer.parseInt(strLine);
                        if (cycles < 0)
                        {
                            System.out.println("Error: first line must be a positive integer.");
                            return;
                        }
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error: first line must be an integer.");
                        return;
                    }
                else
                {
                    splitted = strLine.split(" ");
                    if (splitted.length == 1 && splitted[0].isEmpty())
                        continue;
                    if (splitted.length != 5)
                        throw new Exception("Error: line " + line + ": must be  parameters.");

                    try {
                        aircraftFactory.newAircraft(
                                splitted[0],
                                splitted[1],
                                Integer.parseInt(splitted[2]),
                                Integer.parseInt(splitted[3]),
                                Integer.parseInt(splitted[4])
                        ).registerTower(weatherTower);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error: line " + line + ": must be integers.");
                        return;
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                        return;
                    }
                }
                line++;
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        while (cycles > 0)
        {
            weatherTower.changeWeather();
            cycles--;
        }

        writer.close();
    }
}

