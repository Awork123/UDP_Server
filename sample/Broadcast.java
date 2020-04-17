package sample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Broadcast implements Runnable{
DatagramSocket broadSocket;
DatagramPacket packetSending;
int sendingPort = 7007;
public String message = "Server is ready";


    //Automatic implementation from Runnable
    @Override
    public void run() {

    }

    public void broadcastMessage(String message) throws SocketException {
        try {
            broadSocket = new DatagramSocket();
            byte[] buf = message.getBytes();
            packetSending = new DatagramPacket(buf, buf.length, InetAddress.getByName("255.255.255.255"), sendingPort);
            broadSocket.send(packetSending);
            broadSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}