package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Controller {
    @FXML
    TableView<UdpMessage> table;

    public void startBroadcast() {


    }

    public void clearTheLog() {
        table.getItems().clear();
    }

    public void receiveMessage(UdpMessage udpMessage) {
        table.getItems().add(0, udpMessage);
    }
}
