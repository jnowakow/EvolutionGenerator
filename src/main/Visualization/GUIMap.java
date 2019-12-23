package Visualization;

import Map.JungleMap;
import MapElements.PositonDefinition.Vector2d;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import Visualization.Tile;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GUIMap {
    private JungleMap map;
    private Pane root;

    private final int windowSize = 600;
    private final int animationDelay;
    private Statistics statistics;
    ArrayList<Tile> tilesList = new ArrayList<>();

    public GUIMap(JungleMap map, int animationDelay) {
        this.animationDelay = animationDelay;
        Tile.tileWidht = (windowSize ) / (map.getWidth() + 1);
        Tile.tileHeight = (windowSize) / (map.getHeight() + 1);


        this.map = map;
        this.map.addVisualizer(this);
        this.statistics = new Statistics(map,map.getAnimalsCount(),map.getPlantsCount(), map.avgEnergyLevel());
        visualize();
    }

    private void visualize() {
        root = new Pane();
        root.setPrefSize(windowSize, windowSize);



        for (int i = 0; i < this.map.getWidth() + 1; i++) {
            for (int j = 0; j < this.map.getHeight() + 1; j++) {
                Tile tile;
                Vector2d v = new Vector2d(i,j);

                if ( v.follows(map.lowerLeftJunglePosition) && v.precedes(map.higherRightJunglePosition)) {
                    tile = new Tile(j, i,"", Color.GREEN);
                    tilesList.add(tile);
                }
                else {
                    tile = new Tile(j, i, "",Color.BLACK);
                    tilesList.add(tile);
                }
                root.getChildren().add(tile);
            }
        }



    }

    public Parent visualizeMap(){
        timer.start();
        return root;
    }

    public void update(){
        for(Node child : root.getChildren()){
            Tile tile = (Tile) child;

            Color elementToDraw = map.drawElement(tile.getPosition());

            tile.setColor(elementToDraw);
        }

    }

    private AnimationTimer timer = new AnimationTimer() {

        private long lastTimer = 0;

        @Override
        public void handle(long now) {
            if(now - lastTimer >= animationDelay){
                map.simulateDay();
                statistics.update();
                update();
                lastTimer = now;
            }
        }
    };

}
