import Map.JungleMap;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import static java.lang.System.exit;
import static java.lang.System.setOut;

public class SimulationParameters{
    private int daysCount;
    private int width;
    private int height;
    private double jungleRatio;
    private int initialPlantsCount;
    private int initialAnimalsCount;
    private int animalMoveCost;
    private int animalInitialEnergy;
    private int energyFromPlant;
    private int plantsInJungleSpawn;
    private int plantsInSteppeSpawn;

    public SimulationParameters (String fileName) {

        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException ex)
        {
            System.out.println(fileName + "not found.");
            exit(1);
        }

        JSONObject jo = null;
        try {
            jo = (JSONObject) new JSONParser().parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        daysCount = (int) (long) jo.get("days");
        width = (int) (long) jo.get("width");
        height = (int) (long) jo.get("height");
        jungleRatio = (double) jo.get("jungleRatio");
        initialPlantsCount = (int) (long) jo.get("initialPlantsCount");
        initialAnimalsCount = (int) (long) jo.get("initialAnimalsCount");
        animalMoveCost = (int) (long) jo.get("animalMoveCost");
        animalInitialEnergy = (int) (long) jo.get("animalInitialEnergy");
        energyFromPlant = (int) (long) jo.get("energyFromPlant");
        plantsInJungleSpawn = (int) (long) jo.get("plantsInJungleSpawn");
        plantsInSteppeSpawn = (int) (long) jo.get("plantsInSteppeSpawn");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getJungleRatio() {
        return jungleRatio;
    }

    public int getInitialPlantsCount() {
        return initialPlantsCount;
    }

    public int getInitialAnimalsCount() {
        return initialAnimalsCount;
    }

    public int getAnimalMoveCost() {
        return animalMoveCost;
    }

    public int getAnimalInitialEnergy() {
        return animalInitialEnergy;
    }

    public int getEnergyFromPlant() {
        return energyFromPlant;
    }

    public int getDaysCount() {
        return daysCount;
    }

    public int getPlantsInJungleSpawn() {
        return plantsInJungleSpawn;
    }

    public int getPlantsInSteppeSpawn() {
        return plantsInSteppeSpawn;
    }
}
