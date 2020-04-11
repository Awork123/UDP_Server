package sample;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Broadcast {
    private boolean broadcasting = true;
    private DatagramSocket socket;

    public void broadcast(String broadcastMessage, InetAddress address){
        try {
            socket = new DatagramSocket();
            socket.setBroadcast(true);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
