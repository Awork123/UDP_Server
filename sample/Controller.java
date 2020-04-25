package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import java.util.Random;

public class Controller {
    /* We start out by defining out TableView, GraphicContext and choose our default attributes for the pixel*/
    ObservableList<UdpMessage> commands = FXCollections.observableArrayList();
    private NetworkConnection udpConnector;
    GraphicsContext gc;
    int speedStage = 1;
    public int pixelPositionX = 75;
    public int pixelPositionY = 125;
    public int pixelWidth = 20;
    public int pixelHeight = 20;

    @FXML
    private TableView<UdpMessage> tableView;
    @FXML
    private Canvas canvas;

    /* When we initialize, we set our tableView, draw our canvas and create our Pixel in our default position
     * we then start our connection to the UDP*/
    public void initialize() {
        System.out.println("initialize");
        tableView.setItems(commands);
        gc = canvas.getGraphicsContext2D();
        gc.strokeOval(pixelPositionX, pixelPositionY, pixelWidth, pixelHeight);

        startUdpConnection();
    }

    private void startUdpConnection() {
        if (udpConnector != null) udpConnector.closeSocket();
        udpConnector = new NetworkConnection(this);
        new Thread(udpConnector).start();
    }

    public void receiveMessage(UdpMessage udpMessage) {
        /* When we receive a message, from the NetworkConnection class, we clear the canvas.
         * We then update the tableView, to see what message we got, from which IP and port */
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        UdpMessage command = new UdpMessage(udpMessage.getMessage(), udpMessage.getIp(), udpMessage.getPort());
        tableView.getItems().add(command);

        /* Depending on the message, we manipulate the pixel accordingly*/
        if (udpMessage.getMessage().equals("launch")) {
            System.out.println("Changing Colour");
            Random rand = new Random();
            int red = rand.nextInt(255) + 1;
            int green = rand.nextInt(255) + 1;
            int blue = rand.nextInt(255) + 1;
            gc.setStroke(Color.rgb(red, green, blue));
        } else if (udpMessage.getMessage().equals("move down")) {
            System.out.println("Moving Pixel Down");
            pixelPositionY = pixelPositionY + 2 * speedStage;
        } else if (udpMessage.getMessage().equals("move up")) {
            System.out.println("Moving Pixel Up");
            pixelPositionY = pixelPositionY - 2 * speedStage;
        } else if (udpMessage.getMessage().equals("speed up")) {
            System.out.println("Speeding Up");
            if (speedStage < 10) {
                speedStage = speedStage + 2;
            }
        } else if (udpMessage.getMessage().equals("speed down")) {
            System.out.println("Speeding Down");
            if (speedStage > 1) {
                speedStage = speedStage - 2;
            }
        } else if (udpMessage.getMessage().equals("move right")) {
            System.out.println("Moving Pixel Right");
            pixelPositionX = pixelPositionX + 2;
        } else if (udpMessage.getMessage().equals("move left")) {
            System.out.println("Moving Pixel Left");
            pixelPositionX = pixelPositionX - 2;
        } else {
            System.out.println("Invalid command: '" + udpMessage.getMessage());
        }

        /* After receiving a message, and manipulating an aspect of the pixel, we then redraw it
         * with the new attributes */
        gc.strokeOval(pixelPositionX, pixelPositionY, pixelWidth, pixelHeight);
    }

    /* We made a button, that clears the tableView if pressed.*/
    public void clearLog(ActionEvent actionEvent) {
        tableView.getItems().clear();
    }

    /* We made a button, that resets all the attributes of the pixel.
     To make the black box testing easier and for convenience */
    public void resetPixel(ActionEvent event) {
        System.out.println("Resetting Pixels");
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        speedStage = 1;
        pixelPositionX = 75;
        pixelPositionY = 125;
        pixelWidth = 20;
        pixelHeight = 20;
        gc.strokeOval(pixelPositionX, pixelPositionY, pixelWidth, pixelHeight);
    }
}