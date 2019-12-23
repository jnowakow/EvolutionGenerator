package Map;

import MapElements.Animal.Animal;
import MapElements.IMapElement;
import MapElements.Plant;
import MapElements.PositonDefinition.Vector2d;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractMap implements IPositionChangeObserver, IWorldMap {
    LinkedList<Animal> animalsList = new LinkedList<>();
    HashMap<Vector2d, Plant> plants = new HashMap<>();
    HashMap<Vector2d, LinkedList<Animal>> animals = new HashMap<>();


    @Override
    public void place(Animal animal) {
        animalsList.add(animal);
        animals.computeIfAbsent(animal.getPosition(), pos -> new LinkedList<>()).add(animal);
        animal.addObserver(this);
    }

    public void unregisterAsObserever(Animal animal){
        animal.removeObserver(this);
    }


    @Override
    public void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition){
        animals.get(oldPosition).remove(animal);
        animals.computeIfAbsent(animal.getPosition(), pos -> new LinkedList<>()).add(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if ( (animals.get(position) == null || animals.get(position).isEmpty()) && plants.get(position) == null) {
            return false;
        }
        return true;

    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(downLeft(), upRight());
    }

    @Override
    public Object objectAt(Vector2d position){

        if (animals.get(position) != null && !animals.get(position).isEmpty()){
            return this.animals.get(position);
        }

        return this.plants.get(position);
    }

    public double avgChildrenCount(){
        double sum = 0;
        for (Animal animal : animalsList){
            sum += animal.getChildrenCount();
        }
        return sum / animalsList.size();
    }
    public int avgEnergyLevel(){
        int sum = 0;

        for(Animal animal : animalsList){
            sum+= animal.getEnergyLevel();
        }

        return sum/animalsList.size();
    }
    public int getAnimalsCount(){
        return animalsList.size();
    }

    public int getPlantsCount(){
        return plants.size();
    }

    protected abstract Vector2d upRight();

    protected abstract Vector2d downLeft();

    public Color drawElement(Vector2d position){
        LinkedList<Animal> animalsOnField = animals.get(position);
        if (animalsOnField != null && animalsOnField.size() >=1) {
            Collections.sort(animalsOnField, (animal12, t1) -> {
                return t1.getEnergyLevel() - animal12.getEnergyLevel(); //sort by energy level in descending order
            });
            return animalsOnField.get(0).toColor();
        }

        if(plants.get(position) != null){
            return Color.GREEN;
        }

        return null;

    }
}
