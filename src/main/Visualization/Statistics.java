package Visualization;

import Map.JungleMap;

public class Statistics {
    JungleMap map;
    private int animalsCount;
    private int plantsCount;
    private int energyLevelAvg;
    private double childrenCountAvg;

    public Statistics(JungleMap map, int animalsCount, int plantsCount, int energyLevelAvg){
        this.map = map;
        this.animalsCount = animalsCount;
        this.plantsCount = plantsCount;
        this.energyLevelAvg = energyLevelAvg;
        this.childrenCountAvg = 0;
    }

    public void update(){
        this.animalsCount = map.getAnimalsCount();
        this.plantsCount = map.getPlantsCount();
        this.energyLevelAvg = map.avgEnergyLevel();
        this.childrenCountAvg = map.avgChildrenCount();
    }

    public int getAnimalsCount() {
        return animalsCount;
    }

    public int getPlantsCount() {
        return plantsCount;
    }

    public int getEnergyLevelAvg() {
        return energyLevelAvg;
    }

    public double getChildrenCountAvg() {
        return childrenCountAvg;
    }
}
