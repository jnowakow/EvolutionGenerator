import Map.JungleMap;
import Visualization.GUIMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class World extends Application {
    private static SimulationParameters parameters;

    public static void main(String[] args) {

        parameters = new SimulationParameters(args[0]);

        /*IWorldMap map = new JungleMap(parameters.getWidth(), parameters.getHeight(), parameters.getJungleRatio(), parameters.getInitialPlantsCount(), parameters.getInitialAnimalsCount(), parameters.getAnimalMoveCost(), parameters.getAnimalInitialEnergy(), parameters.getEnergyFromPlant());
        System.out.println(map.toString());

        for (int i = 0; i < parameters.getDaysCount(); i++) {
            map.simulateDay();
            System.out.println(map.toString());

        }*/

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        JungleMap map = new JungleMap(parameters.getWidth(), parameters.getHeight(), parameters.getJungleRatio(), parameters.getInitialPlantsCount(), parameters.getInitialAnimalsCount(), parameters.getAnimalMoveCost(), parameters.getAnimalInitialEnergy(), parameters.getEnergyFromPlant(),parameters.getPlantsInJungleSpawn(), parameters.getPlantsInSteppeSpawn());
        //System.out.println(map.toString());

        GUIMap map1 = new GUIMap(map, 1000000000);

        stage.setScene(new Scene(map1.visualizeMap()));

        stage.show();

        JungleMap map2 = new JungleMap(parameters.getWidth(), parameters.getHeight(), parameters.getJungleRatio(), parameters.getInitialPlantsCount(), parameters.getInitialAnimalsCount(), parameters.getAnimalMoveCost(), parameters.getAnimalInitialEnergy(), parameters.getEnergyFromPlant(),parameters.getPlantsInJungleSpawn(), parameters.getPlantsInSteppeSpawn());
        //System.out.println(map.toString());

        GUIMap map3 = new GUIMap(map2, 1000000000);

        Stage otherWindow = new Stage();
        otherWindow.setScene(new Scene(map3.visualizeMap()));

        otherWindow.show();


    }
}
