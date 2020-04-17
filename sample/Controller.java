package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Controller {
    private GraphicsContext graphicsContext;
    @FXML
    private TableView<Message> table;
    @FXML
    private Canvas canvas;

    public void initialize() {
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    private void draw(GraphicsContext graphicsContext) {
      //  graphicsContext.fillOval(x,y,20,20);
    }






  /*  public void clearTheLog(ActiveEvent event) {
        table.getItems().clear();
        System.out.println("clearing the log");
    }
   */
}

