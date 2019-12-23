package Visualization;

import MapElements.PositonDefinition.Vector2d;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Tile extends StackPane {
    public static int tileWidht = 20;
    public static int tileHeight = 20;

    private String value;
    private Color color;
    private Text text;
    private Rectangle border;

    private Vector2d position;

    public void setValue(String value) {
        this.value = value;
        text.setText(value);
    }

    public void setColor(Color color) {
        this.color = color;
        border.setFill(color);
    }

    public Tile(int x, int y, String value, Color color) {
        this.value = value;
        this.color = color;

        position = new Vector2d(x, y);

        border = new Rectangle(tileWidht, tileHeight);
        border.setFill(color);
        border.setStroke(Color.BLACK);

        text = new Text(value);
        text.setFill(Color.WHITE);

        setTranslateX(x * tileWidht);
        setTranslateY(y * tileHeight);

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);

    }

    public Vector2d getPosition() {
        return position;
    }

    public String getValue() {
        return value;
    }
}
