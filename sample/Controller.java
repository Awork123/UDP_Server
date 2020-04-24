package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Random;

public class Controller {

    //
    ObservableList<UdpMessage> commands = FXCollections.observableArrayList();

    //
    private NetworkConnection udpConnector;
    GraphicsContext gc;
    int speedStage = 1;
    public int pixelPositionX = 75;
    public int pixelPositionY = 125;
    public int pixelWidth = 20;
    public int pixelHeight = 20;

    //
    @FXML
    private TableView<UdpMessage> tableView;
    @FXML
    private Canvas canvas;

    //
    public void initialize() {
        System.out.println("initialize");
        tableView.setItems(commands);
        startUdpConnection();
        gc = canvas.getGraphicsContext2D();
        gc.strokeOval(pixelPositionX, pixelPositionY, pixelWidth, pixelHeight);
    }

    //
    private void startUdpConnection() {
        if (udpConnector != null) udpConnector.closeSocket();
        udpConnector = new NetworkConnection(this);
        new Thread(udpConnector).start();
    }

    public void receiveMessage(UdpMessage udpMessage) {
        //Recieving the incoming messages, ip and port, and shows them at the table
        UdpMessage command = new UdpMessage(udpMessage.getMessage(),udpMessage.getIp(),udpMessage.getPort());
        tableView.getItems().add(command);
        System.out.println(udpMessage);

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        /*The recieved commands from the ESP contains different Strings
        * The accepted commands chooses a random colour, directions and speed for the pixel */
        if (udpMessage.getMessage().equals("launch")) {
            System.out.println("Changing Colour");
            Random rand = new Random();
            int red = rand.nextInt(255) + 1;
            int green = rand.nextInt(255) + 1;
            int blue = rand.nextInt(255) + 1;
            gc.setStroke(Color.rgb(red, green, blue));
        }

        else if (udpMessage.getMessage().equals("move down")) {
            System.out.println("Moving Pixel Down");
            pixelPositionY = pixelPositionY + 2 * speedStage;
        }

        else if (udpMessage.getMessage().equals("move up")) {
            System.out.println("Moving Pixel Up");
            pixelPositionY = pixelPositionY - 2 * speedStage;
        }

        else if (udpMessage.getMessage().equals("speed up")) {
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
        }

        else if (udpMessage.getMessage().equals("move left")) {
            System.out.println("Moving Pixel Left");
            pixelPositionX = pixelPositionX - 2;
            System.out.println("");
        } else {
            System.out.println("Invalid command: '" + udpMessage.getMessage());
        }

        //Saves the last placement for the pixel
        gc.strokeOval(pixelPositionX, pixelPositionY, pixelWidth, pixelHeight);

    }

    //Clearing the tableView
    public void clearLog(ActionEvent actionEvent) {
        System.out.println("Clearing Log");
        tableView.getItems().clear();

    }


    // If the pixel is lost, reset its attributes
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