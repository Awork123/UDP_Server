package sample;

public class Message {
    private String message;
    private String IP;
    private int port;
    private int length;

    public Message(String message, String IP, int port, int length) {
        this.message = message;
        this.IP = IP;
        this.port = port;
        this.length = length;
    }

    public String getMessage() {
        return message;
    }

    public String getIP() {
        return IP;
    }
    //Any reason to getport?
    public int getPort() {
        return port;
    }

    public int getLength() {
        return length;
    }
}
