package Map;

import MapElements.Animal.Animal;
import MapElements.IMapElement;
import MapElements.Plant;
import MapElements.PositonDefinition.Vector2d;
import javafx.util.Pair;

import java.util.*;

public class JungleMap extends AbstractMap {
    private final int width;
    private final int height;
    private final int jungleArea;
    private final Vector2d lowerLeftJunglePosition;
    private final Vector2d higherRightJunglePosition;
    private final int energyLoss;
    private final int initialEnergy;
    private final int energyFromPlant;


    public JungleMap(int width, int height, double jungleRatio, int initialPlantCount, int initialAnimalCount, int energyLoss, int initialEnergy, int energyFromPlant) {
        this.energyLoss = energyLoss;
        this.initialEnergy = initialEnergy;
        this.energyFromPlant = energyFromPlant;

        this.width = width;
        this.height = height;

        int jungleWidth = (int) Math.round(width * jungleRatio);
        int jungleHeight = (int) Math.round(height * jungleRatio);
        this.jungleArea = jungleHeight * jungleWidth;

        this.lowerLeftJunglePosition = new Vector2d((width - jungleWidth) / 2, (height - jungleHeight) / 2);
        this.higherRightJunglePosition = new Vector2d((width + jungleWidth) / 2, (height + jungleHeight) / 2);

        while (initialAnimalCount > 0) {
            Animal animal = new Animal(this.width, this.height, this.initialEnergy);
            if (isOccupied(animal.getPosition())) continue;

            place(new Animal(this.width, this.height, this.initialEnergy));
            initialAnimalCount--;
        }


        //place half plants in jungle
        for (int i = 0; i < initialPlantCount / 2 ; i++) {
            putPlantInJungle();
        }
        //and another half in steppe
        for (int i = initialPlantCount / 2; i < initialPlantCount; i++) {
            putPlantInSteppe();
        }


    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void putPlantInJungle() {
        Random random = new Random();
        //get random coordinates of plant in jungle area
        int x = random.nextInt(higherRightJunglePosition.x - lowerLeftJunglePosition.x + 1)  + lowerLeftJunglePosition.x;
        int y = random.nextInt(higherRightJunglePosition.y - lowerLeftJunglePosition.y + 1) + lowerLeftJunglePosition.y;
        Vector2d position = new Vector2d(x, y);
        //try to find free space in jungle. If the place would't be found in number of attempts equal to jungle area
        //it is assumed that jungle is full
        int counter = 0;
        while (isOccupied(position) && counter < 2*jungleArea) {
            x = random.nextInt(higherRightJunglePosition.x - lowerLeftJunglePosition.x + 1) + lowerLeftJunglePosition.x;
            y = random.nextInt(higherRightJunglePosition.y - lowerLeftJunglePosition.y + 1) + lowerLeftJunglePosition.y;
            position = new Vector2d(x, y);
            counter++;
        }
        //if it wasn't possible to find free position don't put new plant
        if (isOccupied(position)) {
            return;
        }

        //add plant to hash map and list
        Plant plant = new Plant(position);
        this.plants.putIfAbsent(position, plant);

    }

    private void putPlantInSteppe() {
        Random random = new Random();
        //get random coordinates of plant
        int x = random.nextInt(this.width + 1);
        int y = random.nextInt(this.height + 1);
        Vector2d position = new Vector2d(x, y);

        //while the position is in jungle or is occupied get the new position
        while (isOccupied(position) && !(position.precedes(this.higherRightJunglePosition) && position.follows(this.lowerLeftJunglePosition))) {
            x = random.nextInt(this.width + 1);
            y = random.nextInt(this.height + 1);
            position = new Vector2d(x, y);
        }

        //add plant to hash map and list
        Plant plant = new Plant(position);
        this.plants.putIfAbsent(position, plant);
    }

    public void simulateDay() {
        //remove dead animals
        LinkedList<Animal> deadAnimals = new LinkedList<>();

        animalsList.forEach(animal -> { if (animal.isDead()) { deadAnimals.add(animal); }});

        deadAnimals.forEach(animal -> {animals.remove(animal);
            animals.get(animal.getPosition()).remove(animal);});

        //move each animals
        animalsList.forEach(animal -> animal.move(this, energyLoss));

        //feed animals
        LinkedList<Vector2d> eatenPlantsPositions = new LinkedList<>();

        for (Map.Entry<Vector2d, Plant> positionPlant : plants.entrySet()) {
            Vector2d position = positionPlant.getKey();
            Plant plant = positionPlant.getValue();


            if (animals.get(position) != null && animals.get(position).size() > 0) {

                eatenPlantsPositions.add(position);

                System.out.println("I'm eaten" + plant.getPosition()) ;

                LinkedList<Animal> animalsOnField = animals.get(position);//There can't be nothing more on list since plant is removed
                LinkedList<Animal> animalsToFeed = new LinkedList<>();



                int max = -1;
                for (Animal animal : animalsOnField) {
                    if (animal.getEnergyLevel() > max && animal.getEnergyLevel() != initialEnergy){
                        max = animal.getEnergyLevel();
                        animalsToFeed.clear();
                        animalsToFeed.add(animal);
                    }
                    else if (animal.getEnergyLevel() == max) {
                        animalsToFeed.add(animal);
                    }
                }

                animalsToFeed.forEach(animal -> animal.eatPlant(energyFromPlant / animalsToFeed.size()));

            }
        }

        eatenPlantsPositions.forEach(position -> plants.remove(position));

        //choose animals, which will reproduce
        HashSet<Pair<Animal, Animal>> pairsToReproduce = findPairsToReproduce();

        for(Pair<Animal, Animal> pair : pairsToReproduce){
            Animal parent1 = pair.getKey();
            Animal parent2 = pair.getValue();

            place(parent1.reproduce(parent2, this.bornPosition(parent1.getPosition())));
        }


        //add new plants to map
        putPlantInJungle();
        putPlantInSteppe();

    }

    private HashSet<Pair<Animal, Animal>> findPairsToReproduce(){
        HashSet<Pair<Animal, Animal>> pairsToReproduce = new HashSet<>();

        for (Animal animal : animalsList){
            if (animals.get(animal.getPosition()).size() > 1){ //it is after eating so there won't be any plant on field with animal
                LinkedList<Animal> animalsOnField = animals.get(animal.getPosition());

                Collections.sort(animalsOnField, (animal12, t1) -> {
                    return t1.getEnergyLevel() - animal12.getEnergyLevel(); //sort by energy level in descending order
                });

                //animalsOnField.forEach(animal1 -> System.out.println(animal1 + " aa " + animal1.getEnergyLevel()));

                Animal animal1 = animalsOnField.get(0);
                Animal animal2 = animalsOnField.get(1);
                if (animal1.getEnergyLevel() >= 0.5 * initialEnergy && animal2.getEnergyLevel() >= 0.5 * initialEnergy ) {
                    pairsToReproduce.add(new Pair<>(animal1, animal2));
                }
            }
        }
        //System.out.println(pairsToReproduce);
        return pairsToReproduce;
    }


    private Vector2d bornPosition(Vector2d position) {
        //check each neighbour position
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Vector2d bornPosition = new Vector2d(position.x + i, position.y + j);
                if (!isOccupied(bornPosition)) return validatePosition(bornPosition);
            }
        }

        //if there was no free place randomly choose from neighbouring positions
        Random random = new Random();
        int i = random.nextInt(3) - 1;
        int j = random.nextInt(3) - 1;
        return validatePosition(new Vector2d(position.x + i, position.y + j));
    }

    @Override
    public Vector2d validatePosition(Vector2d position) { // if animal go out of map it will appear on the other side
        if (position.x > this.width && position.y > this.height) return new Vector2d(0, 0);
        else if (position.x > this.width && position.y < 0) return new Vector2d(0, this.height);
        else if (position.x < 0 && position.y < 0) return new Vector2d(this.width, this.height);
        else if (position.x < 0 && position.y > this.height) return new Vector2d(this.width, 0);
        else if (position.x > this.width) return new Vector2d(0, position.y);
        else if (position.x < 0) return new Vector2d(this.width, position.y);
        else if (position.y < 0) return new Vector2d(position.x, this.height);
        else if (position.y > this.height) return new Vector2d(position.x, 0);

        else return position;
    }

    @Override
    public Vector2d downLeft() {
        return new Vector2d(0, 0);
    }

    @Override
    public Vector2d upRight() {
        return new Vector2d(width, height);
    }

}
