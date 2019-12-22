package Map;

import MapElements.Animal.Animal;
import MapElements.PositonDefinition.Vector2d;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IWorldMap {
    /**
     * Check if given position lies in map's bounds. If not returns position on the other side of map
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */

    Vector2d validatePosition(Vector2d position);

    /**
     * Place a animal on the map.
     *
     * @param animal
     *            The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map is already occupied.
     */

    /**
     * Simulates day. Remove dead animals, move every animal, make animals eat and reproduce add new plants
     *
     */

    void simulateDay();

    void place(Animal animal);


    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position
     *            Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d postion);

}