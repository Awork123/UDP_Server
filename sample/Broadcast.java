package sample;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.*;

public class Broadcast implements Runnable{
    private boolean broadcasting = true;
    private DatagramSocket socket;
    private int broadcastPort = 7007;
    private String message = "Listening";


    public void broadcasting(){
        try {
            socket = new DatagramSocket();
            socket.setBroadcast(true);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


    public void broadcastingMessage() {
        try {
            socket = new DatagramSocket();
            byte[] messageBytes = message.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(messageBytes, messageBytes.length, InetAddress.getByName("255.255.255.2550") ,7007);
            socket.send(datagramPacket);
            socket.close();
            System.out.println("brodcasting");

        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }


}
