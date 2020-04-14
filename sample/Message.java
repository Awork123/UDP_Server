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

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
//Any reason to get / set port?
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", IP='" + IP + '\'' +
                ", port=" + port +
                ", length=" + length +
                '}';
    }
}