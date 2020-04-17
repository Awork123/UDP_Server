package sample;

import javafx.scene.canvas.GraphicsContext;

public class Emulator {
    private int x;
    private int y;

    public Emulator(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.fillOval(x,y,20,20);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Emulator{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
