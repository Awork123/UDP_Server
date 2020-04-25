package sample;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/* We define our parameters*/
public class UdpMessage {
    private String time;
    private String message;
    private String ip;
    private int port;

    /* We set our message, ip, time and port. For the time, we use a library to make it cleaner
     * and to make the formatting easier */
    public UdpMessage(String message, String ip, int port) {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date time = new Date();
        this.time =formatter.format(time);
        this.message = message;
        this.ip = ip;
        this.port = port;
    }

    /* Here we create the constructor that contains all the information about the message we receive, that
     * that we use in the NetworkConnection, receiveMessage method */
    public UdpMessage(byte[] message, int msgLength, InetAddress ip, int port) {
        this(new String(message, 0, msgLength), ip.getHostAddress(), port );
    }

    /* We create 3 constructors, that return the message, ip and port, so we can get those in our controller class
     * and thereby update the TableView with information*/
    public String getMessage() {
        return message;
    }
    public String getIp() {
        return ip;
    }
    public int getPort() {
        return port;
    }

    /* We create a constructor, that returns the string, that gives all the information about the message we received*/
    @Override
    public String toString() {
        return "Received: " + message + " From {" +
                "ip='" + ip + '\'' +
                ", port='" + port +'\''+
                ", time='" + time +'\'' +
                '}';
    }
}