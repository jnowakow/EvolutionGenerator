package MapElements.PositonDefinition;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapDirectionTest {

    @Test
    public void next() {
        MapDirection direction;

        direction = MapDirection.NORTHWEST;
        assertEquals(direction.next(), MapDirection.NORTH);

        direction = MapDirection.NORTH;
        assertEquals(direction.next(), MapDirection.NORTHEAST);

        direction = MapDirection.NORTHEAST;
        assertEquals(direction.next(), MapDirection.EAST);

        direction = MapDirection.EAST;
        assertEquals(direction.next(), MapDirection.SOUTHEAST);

        direction = MapDirection.SOUTHEAST;
        assertEquals(direction.next(), MapDirection.SOUTH);

        direction = MapDirection.SOUTH;
        assertEquals(direction.next(), MapDirection.SOUTHWEST);

        direction = MapDirection.SOUTHWEST;
        assertEquals(direction.next(), MapDirection.WEST);

        direction = MapDirection.WEST;
        assertEquals(direction.next(), MapDirection.NORTHWEST);
    }

    @Test
    public void previous() {
        MapDirection direction;

        direction = MapDirection.NORTHWEST;
        assertEquals(direction.previous(), MapDirection.WEST);

        direction = MapDirection.NORTH;
        assertEquals(direction.previous(), MapDirection.NORTHWEST);

        direction = MapDirection.NORTHEAST;
        assertEquals(direction.previous(), MapDirection.NORTH);

        direction = MapDirection.EAST;
        assertEquals(direction.previous(), MapDirection.NORTHEAST);

        direction = MapDirection.SOUTHEAST;
        assertEquals(direction.previous(), MapDirection.EAST);

        direction = MapDirection.SOUTH;
        assertEquals(direction.previous(), MapDirection.SOUTHEAST);

        direction = MapDirection.SOUTHWEST;
        assertEquals(direction.previous(), MapDirection.SOUTH);

        direction = MapDirection.WEST;
        assertEquals(direction.previous(), MapDirection.SOUTHWEST);

    }
    @Test
    public void toUnitVector() {
        MapDirection direction;

        direction = MapDirection.NORTH;
        assertEquals(direction.toUnitVector(), new Vector2d(0,1));

        direction = MapDirection.NORTHEAST;
        assertEquals(direction.toUnitVector(), new Vector2d(1,1));

        direction = MapDirection.EAST;
        assertEquals(direction.toUnitVector(), new Vector2d(1,0));

        direction = MapDirection.SOUTHEAST;
        assertEquals(direction.toUnitVector(), new Vector2d(1,-1));

        direction = MapDirection.SOUTH;
        assertEquals(direction.toUnitVector(), new Vector2d(0,-1));

        direction = MapDirection.SOUTHWEST;
        assertEquals(direction.toUnitVector(), new Vector2d(-1,-1));

        direction = MapDirection.WEST;
        assertEquals(direction.toUnitVector(), new Vector2d(-1,0));

        direction = MapDirection.NORTHWEST;
        assertEquals(direction.toUnitVector(), new Vector2d(-1,1));

    }
}