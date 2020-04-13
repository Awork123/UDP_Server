package sample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Connection {
    DatagramSocket connSocket;
    DatagramPacket packetRecieving;
    int recievingPort = 7000;
    int sendingPort = 7007;


    public void messageRecieving() throws IOException {
        connSocket = new DatagramSocket(recievingPort);
        byte[] bytes = new byte[256];
        packetRecieving = new DatagramPacket(bytes, bytes.length);
        connSocket.receive(packetRecieving);

    }
}