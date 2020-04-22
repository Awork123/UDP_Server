package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {
    private

    ObservableList<UdpMessage> commands = FXCollections.observableArrayList();
    private NetworkConnection udpConnector;
    GraphicsContext gc;

    public void initialize()
    {
        System.out.println("initialize");
        tableView.setItems(commands);
        startUdpConnection();
        gc = canvas.getGraphicsContext2D();
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
        ///UdpMessage command = new UdpMessage(tableMessage.getText(), tableIP.getText(), tablePort.getText());
        //tableView.getItems().add(command);
        System.out.println(udpMessage);

        if (udpMessage.getMessage().equals("launch")){
            System.out.println("Changing Color on Pixel");
            gc.strokeOval(10, 10, 20, 20);
            //farve
        }
        else if (udpMessage.getMessage().equals("move down")){
            //System.out.println("Moving Pixel Down");

        }
        else if (udpMessage.getMessage().equals("move up")){
            //System.out.println("Moving Pixel up");

        }
        else if (udpMessage.getMessage().equals("speed up")) {
            // System.out.println("Increasing Movement Speed of Pixel");

        }
        else if (udpMessage.getMessage().equals("speed down")){
            //  System.out.println("Decreasing Movement Speed of Pixel");

        }
        else if (udpMessage.getMessage().equals("change shape")){
            // System.out.println("Changing Shape of Pixel");

        }

        else {
            // System.out.println("Invalid command: '"+ command + "' : " + message);
            System.out.println();
        }

    }


}