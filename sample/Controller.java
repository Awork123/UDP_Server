package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.canvas.*;

import java.awt.Canvas;

public class Controller {
    Broadcast broadcast;

    @FXML
    TableView<UdpMessage> table;
    @FXML
    private Canvas canvas;

    GraphicsContext graphicContextCanvas;

    public void initialize() {
        broadcast = new Broadcast();
        new Thread(broadcast).start();
    }


    public void clearTheLog() {
        System.out.println("clearing the log");
    }
}

