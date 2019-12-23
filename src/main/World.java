import Map.IWorldMap;
import Map.JungleMap;
import MapElements.Animal.Animal;
import MapElements.Animal.Genes;
import MapElements.PositonDefinition.Vector2d;

public class World {
    public static void main(String[] args) {
        SimulationParameters parameters = new SimulationParameters(args[0]);
        IWorldMap map = new JungleMap(parameters.getWidth(), parameters.getHeight(), parameters.getJungleRatio(), parameters.getInitialPlantsCount(), parameters.getInitialAnimalsCount(), parameters.getAnimalMoveCost(), parameters.getAnimalInitialEnergy(), parameters.getEnergyFromPlant());
        System.out.println(map.toString());

        for (int i = 0; i < parameters.getDaysCount(); i++) {
            map.simulateDay();
            System.out.println(map.toString());

        }
    }
}
