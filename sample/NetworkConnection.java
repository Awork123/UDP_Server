package sample;

import java.io.IOException;
import java.net.*;

/* We define our parameters and makes the function be runnable, which will do the connection loop*/
class NetworkConnection implements Runnable {
    private DatagramSocket socket;
    private int udpPort = 4444;
    private Controller messageHandler;
    private boolean receiveMessages = true;

    /* We set up methods that close and open sockets, so we can receive our messages from the UDP*/
    public NetworkConnection(Controller messageHandler) {
        this.messageHandler = messageHandler;
        setupSocket();
    }

    public void closeSocket() {
        this.socket.close();
    }

    public void setupSocket() {
        try {
            socket = new DatagramSocket(udpPort);
        } catch (SocketException e) {
            System.out.println("IOEXCEPTION: Tried to create new datagramsocket on " + udpPort);
            System.out.println(e.getMessage());
        }
    }

    /* When we receive a message from the UDP, we return the message to the controller class
     * and then close the socket. */
    public UdpMessage receiveMessage() {
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            socket = new DatagramSocket(udpPort);
            socket.receive(packet);
            UdpMessage message = new UdpMessage(packet.getData(), packet.getLength(), packet.getAddress(), packet.getPort());
            messageHandler.receiveMessage(message);
            return message;
        } catch (IOException e) {
            return null;
        } finally {
            socket.close();
        }
    }

    /* We begin our connectionLoop, which will wait till a packet is received.
     * when a packet is received, we close our socket*/
    @Override
    public void run() {
        connectionLoop();
    }

    public void connectionLoop() {
        System.out.println("Started UdpConnector Thread");

        do {
            receiveMessage();
        }

        while (isReceiveMessages());
        socket.close();
    }

    public boolean isReceiveMessages() {
        return receiveMessages;
    }
}