package MapElements.PositonDefinition;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        return  "("+this.x+","+this.y+")";
    }

    public boolean precedes(Vector2d other){
        if (this.x <= other.x && this.y <= other.y)
            return true;
        else return false;
    }

    public boolean follows(Vector2d other){
        if (this.x >= other.x && this.y >= other.y)
            return true;
        else return false;
    }

    public Vector2d upperRight(Vector2d other){
        Vector2d upper = new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
        return  upper;
    }

    public Vector2d lowerLeft(Vector2d other){
        Vector2d lower = new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
        return  lower;
    }

    public Vector2d add(Vector2d other){
        Vector2d res = new Vector2d(this.x + other.x, this.y + other.y);
        return res;
    }

    public Vector2d substract(Vector2d other){
        Vector2d res = new Vector2d(this.x - other.x, this.y - other.y);
        return res;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result += 23 * result + 3 * this.x;
        result += 29 * result + 7 * this.y;
        return  result;
    }

    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;

        return this.x == that.x && this.y == that.y;
    }

    public Vector2d opposite(){
        Vector2d vec = new Vector2d(-this.x, -this.y);
        return vec;
    }
}