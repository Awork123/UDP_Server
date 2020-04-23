package sample;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpMessage {
    private String time;
    private String message;
    private String ip;
    private int length;
    private int port;

    public UdpMessage(String message, String ip, int port)
    {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date time = new Date();
        this.time =formatter.format(time);
        this.message = message;
        this.ip = ip;
        this.length = message.length();
        this.port = port;

    }

    public UdpMessage(byte[] message, int msgLength, InetAddress ip, int port)
    {
        this(new String(message, 0, msgLength), ip.getHostAddress(), port );
    }
/*
    public UdpMessage(String message, String ip, String port) {
        this.message = message;
        this.ip = ip;
        this.port = Integer.parseInt(port);
    }  */

    public String getMessage() {
        return message;
    }
    public String getIp() {
        return ip;
    }
    public int getPort() { return port; }

    @Override
    public String toString() {
        return "From {" +
                "ip='" + ip + '\'' +
                ", port='" + port +'\''+
                ", time='" + time +'\'' +
                '}';
    }
}