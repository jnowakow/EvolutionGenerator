import Map.IWorldMap;
import Map.JungleMap;
import MapElements.Animal.Animal;
import MapElements.Animal.Genes;
import MapElements.PositonDefinition.Vector2d;

public class world {
    public static void main(String[] args) {
        IWorldMap map = new JungleMap(5, 5, 0.25, 7,6, 1, 4, 1);
        System.out.println(map.toString());
        for (int i = 0; i < 5; i++) {
            map.simulateDay();
            System.out.println(map.toString());

        }
    }
}
