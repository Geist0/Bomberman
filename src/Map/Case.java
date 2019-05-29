package Map;

import javafx.scene.paint.Color;

public class Case {
    private int id;
    private Color color;
    private boolean passage;

    public Case(int id,Color color,boolean passage) {
        this.id = id;
        this.color = color;
        this.passage=passage;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public void setPassage(boolean passage) {
        this.passage = passage;
    }

    public boolean isPassage() {
        return passage;
    }
}
