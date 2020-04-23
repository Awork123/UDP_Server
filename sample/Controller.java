package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Random;

public class Controller {

    ObservableList<UdpMessage> commands = FXCollections.observableArrayList();

    private NetworkConnection udpConnector;
    GraphicsContext gc;
    int speedStage = 1;
    public int pixelPositionX = 175;
    public int pixelPositionY = 100;
    public int pixelWidth = 20;
    public int pixelHeight = 20;
    public void initialize()
    {
        System.out.println("initialize");
        tableView.setItems(commands);
        startUdpConnection();
        gc = canvas.getGraphicsContext2D();
        gc.strokeOval(pixelPositionX, pixelPositionY, pixelWidth, pixelHeight);
    }

    @FXML
    private TableView<UdpMessage> tableView;

    @FXML
    private TextField tableMessage;

    @FXML
    private TextField tableIP;

    @FXML
    private TextField tablePort;

    @FXML
    private Canvas canvas;

    private void startUdpConnection() {
        if (udpConnector != null) udpConnector.closeSocket();
        udpConnector = new NetworkConnection(this);
        new Thread(udpConnector).start();
    }

    public void receiveMessage(UdpMessage udpMessage){
        System.out.println(udpMessage);
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

        UdpMessage command = new UdpMessage(udpMessage.getMessage(),udpMessage.getIp(),udpMessage.getPort());
       // UdpMessage command = new UdpMessage(tableMessage.getText(),tableIP.getText(), tablePort.getLength());
        tableView.getItems().add(command);



        if (udpMessage.getMessage().equals("launch")){
            Random rand = new Random();
            int red = rand.nextInt(255)+1;
            int green= rand.nextInt(255)+1;
            int blue= rand.nextInt(255)+1;
            System.out.println("Changing Color on Pixel");
            gc.setStroke(Color.rgb(red, green, blue));
        }
        else if (udpMessage.getMessage().equals("move down")){
            pixelPositionY = pixelPositionY +2*speedStage;

        }
        else if (udpMessage.getMessage().equals("move up")){
            pixelPositionY = pixelPositionY -2*speedStage;

        }
        else if (udpMessage.getMessage().equals("speed up")) {
            if (speedStage < 10) {
                speedStage = speedStage +2;
            }
        }
        else if (udpMessage.getMessage().equals("speed down")){
            if (speedStage > 1) {
                speedStage = speedStage - 2;
            }

        }
        else if (udpMessage.getMessage().equals("StrokeSize decrease")){
            // System.out.println("Changing Shape of Pixel");
        }
        else if (udpMessage.getMessage().equals("StrokeSize increase")){
            // System.out.println("Changing Shape of Pixel");
        }

        else {
            // System.out.println("Invalid command: '"+ command + "' : " + message);
            //System.out.println();
        }

        gc.strokeOval(pixelPositionX, pixelPositionY, pixelWidth, pixelHeight);

    }


}