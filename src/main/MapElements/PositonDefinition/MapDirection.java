package MapElements.PositonDefinition;

public enum MapDirection {
    NORTHWEST,
    NORTH,
    NORTHEAST,
    SOUTHWEST,
    SOUTH,
    SOUTHEAST,
    WEST,
    EAST;

    public String toString(){
        switch (this){
            case NORTHEAST: return "North-East";
            case NORTH: return "North";
            case NORTHWEST: return "North-West";
            case SOUTHEAST: return "South-East";
            case SOUTH: return "South";
            case SOUTHWEST: return "South-West";
            case EAST: return "East";
            case WEST: return "West";
            default: return "";
        }
    }

    public MapDirection next(){
        switch (this){
            case NORTHEAST: return MapDirection.EAST;
            case NORTH: return MapDirection.NORTHEAST;
            case NORTHWEST: return MapDirection.NORTH;
            case SOUTHEAST: return MapDirection.SOUTH;
            case SOUTH: return MapDirection.SOUTHWEST;
            case SOUTHWEST: return MapDirection.WEST;
            case EAST: return MapDirection.SOUTHEAST;
            case WEST: return MapDirection.NORTHWEST;
            default: return null;
        }
    }

    public MapDirection previous(){
        switch (this){
            case NORTHEAST: return MapDirection.NORTH;
            case NORTH: return MapDirection.NORTHWEST;
            case NORTHWEST: return MapDirection.WEST;
            case SOUTHEAST: return MapDirection.EAST;
            case SOUTH: return MapDirection.SOUTHEAST;
            case SOUTHWEST: return MapDirection.SOUTH;
            case EAST: return MapDirection.NORTHEAST;
            case WEST: return MapDirection.SOUTHWEST;
            default: return null;
        }
    }

    public Vector2d toUnitVector(){
        switch (this){
            case NORTHEAST: return new Vector2d(1,1);
            case NORTH: return new Vector2d(0,1);
            case NORTHWEST: return new Vector2d(-1,1);
            case SOUTHEAST: return new Vector2d(1,-1);
            case SOUTH: return new Vector2d(0,-1);
            case SOUTHWEST: return new Vector2d(-1,-1);
            case EAST: return new Vector2d(1,0);
            case WEST: return new Vector2d(-1,0);
            default: return null;
        }


    }

}
