package sample;

import java.io.IOException;
import java.net.*;

class NetworkConnection implements Runnable{
    private DatagramSocket socket;
    private int udpPort = 4444;
    private Controller messageHandler;
    private boolean receiveMessages = true;


    public NetworkConnection(Controller messageHandler)
    {
        this.messageHandler = messageHandler;
        setupSocket();
    }

    public void closeSocket()
    {
        this.socket.close();
    }

    public void setupSocket() {
        try {
            socket = new DatagramSocket(udpPort);
        }

        catch (SocketException e) {
            System.out.println("IOEXCEPTION: Tried to create new datagramsocket on "+udpPort);
            System.out.println(e.getMessage());
        }
    }

    public UdpMessage receiveMessage() {
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            socket = new DatagramSocket(udpPort);
            socket.receive(packet);
            UdpMessage message = new UdpMessage(packet.getData(), packet.getLength(), packet.getAddress() , packet.getPort());


            messageHandler.receiveMessage(message);
            return message;
        }



        catch (IOException e) {
            return null;
        }

        finally {
            socket.close();
        }
    }
    @Override
    public void run() {
        connectionLoop();
    }

    public void connectionLoop() {
        System.out.println("Started UdpConnector Thread");
        do
        {
            receiveMessage();
        }
        while(isReceiveMessages());
        socket.close();
    }

    public boolean isReceiveMessages() {
        return receiveMessages;
    }
}