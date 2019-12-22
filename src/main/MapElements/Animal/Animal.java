package MapElements.Animal;

import Map.IPositionChangeObserver;
import Map.IWorldMap;
import MapElements.IMapElement;
import MapElements.PositonDefinition.MapDirection;
import MapElements.PositonDefinition.Vector2d;

import java.util.LinkedList;
import java.util.Random;

public class Animal implements IMapElement {
    private Vector2d position;
    private MapDirection direction;
    public int childrenCount = 0;
    private int energyLevel;
    private Genes genome;
    private LinkedList<IPositionChangeObserver> observers = new LinkedList<>();


    public Animal(int mapWidth, int mapHeight, int initialEnergy){
        Random random = new Random();
        int r = random.nextInt(8);
        //direction has to be random
        this.direction = MapDirection.NORTH;
        while (r > 0){
            this.direction = this.direction.next();
            r--;
        }

        int x = random.nextInt(mapWidth+1);
        int y = random.nextInt(mapHeight+1);
        this.position = new Vector2d(x,y);
        this.energyLevel = initialEnergy;
        this.genome = new Genes();
    }

    private Animal(Vector2d initialPosition, Animal parent1, Animal parent2){
        Random random = new Random();
        int r = random.nextInt(8);
        //direction has to be random
        this.direction = parent1.direction;
        while (r>0){
            this.direction = this.direction.next();
            r--;
        }

        this.position = initialPosition;
        this.genome = new Genes(parent1.genome, parent2.genome);
        this.energyLevel += 0.25 * parent1.energyLevel + 0.25 * parent2.energyLevel;
    }


    public void eatPlant(int bonusEnergy){
        this.energyLevel += bonusEnergy;
    }

    public Animal reproduce(Animal other, Vector2d bornPosition){
        Animal child = new Animal(bornPosition, this, other);

        this.energyLevel -= 0.25*this.energyLevel;
        other.energyLevel -= 0.25*other.energyLevel;

        //System.out.println("miracle of nature " + child.getPosition() + " " + child.getEnergyLevel());
        return child;
    }

    public void move(IWorldMap map, int energyLoss){
        int turnsNumber = this.genome.generateNumberOfTurns();
        while ( turnsNumber > 0){
            this.direction = this.direction.next();
            turnsNumber--;
        }
        Vector2d oldPosition = this.position;

        this.position = map.validatePosition(this.position.add(this.direction.toUnitVector()));

        positionChanged(oldPosition, this.position);

        this.energyLevel -= energyLoss;
    }

    public boolean isDead(){
        return this.energyLevel <= 0;
    }

    public void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : observers){
            observer.positionChanged(this, oldPosition, newPosition);
        }
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public int getEnergyLevel() { return this.energyLevel; }


    @Override
    public String toString(){
        switch (this.direction){
            case NORTHEAST: return "NE";
            case NORTH: return "N";
            case NORTHWEST: return "NW";
            case SOUTHEAST: return "SE";
            case SOUTHWEST: return "SW";
            case SOUTH: return "S";
            case EAST: return "E";
            case WEST: return "W";
            default: return "";
        }
    }

}
