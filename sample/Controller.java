package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.canvas.*;

import java.awt.Canvas;

public class Controller {
    @FXML
    TableView<Broadcast> table;

    Broadcast broadcast;
    private Canvas canvas;

    GraphicsContext graphicContextCanvas;

    public void initialize() {

    }


    public void clearTheLog() {
        System.out.println("clearing the log");
    }
}

