package MapElements.PositonDefinition;

import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2dTest {

    @Test
    public void testToString() {
        Vector2d vec = new Vector2d(1, 2);
        assertEquals(vec.toString(), "(" + vec.x + "," + vec.y + ")");
    }

    @Test
    public void precedes() {
        Vector2d vec1 = new Vector2d(1, 2);
        Vector2d vec2 = new Vector2d(2, 3);
        assertTrue(vec1.precedes(vec2));
    }

    @Test
    public void follows() {
        Vector2d vec1 = new Vector2d(2, 3);
        Vector2d vec2 = new Vector2d(1, 2);
        assertTrue(vec1.follows(vec2));
    }

    @Test
    public void upperRight() {
        Vector2d vec1 = new Vector2d(1, 2);
        Vector2d vec2 = new Vector2d(2, 3);
        Vector2d upper = new Vector2d(Math.max(vec1.x, vec2.x), Math.max(vec1.y, vec2.y));
        assertEquals(vec1.upperRight(vec2), upper);
    }

    @Test
    public void lowerLeft() {
        Vector2d vec1 = new Vector2d(1, 2);
        Vector2d vec2 = new Vector2d(2, 3);
        Vector2d lower = new Vector2d(Math.min(vec1.x, vec2.x), Math.min(vec1.y, vec2.y));
        assertEquals(vec1.lowerLeft(vec2), lower);
    }

    @Test
    public void add() {
        Vector2d vec1 = new Vector2d(1, 2);
        Vector2d vec2 = new Vector2d(2, 3);
        Vector2d sum = new Vector2d(vec1.x + vec2.x, vec1.y + vec2.y);
        assertEquals(vec1.add(vec2), sum);
    }

    @Test
    public void substract() {
        Vector2d vec1 = new Vector2d(1, 2);
        Vector2d vec2 = new Vector2d(2, 3);
        Vector2d diff = new Vector2d(vec1.x - vec2.x, vec1.y - vec2.y);
        assertEquals(vec1.substract(vec2), diff);
    }

    @Test
    public void testEquals() {
        Vector2d vec1 = new Vector2d(1, 2);
        Vector2d vec2 = new Vector2d(2, 3);
        assertFalse(vec1.equals(vec2));
    }

    @Test
    public void opposite() {
        Vector2d vec1 = new Vector2d(1, 2);
        Vector2d vec2 = new Vector2d(-vec1.x, -vec1.y);

        assertEquals(vec1.opposite(), vec2);
    }
}