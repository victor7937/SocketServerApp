package by.victor.jwd.client;

import by.victor.jwd.entity.RequestObject;
import java.io.*;
import java.net.Socket;


public class Client {
    private static final int port = 4040;
    private static final String hostName = "localhost";
    private Socket socket;

    public Client () {
        try {
            socket = new Socket(hostName,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendRequest (RequestObject requestObject){
        String request = "";
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {

            objectOutputStream.writeObject(requestObject);
            objectOutputStream.flush();
            request = objectInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static void main(String[] args) {
        Client client = new Client();
        RequestObject ro = new RequestObject(11,"a b");
        System.out.println(client.sendRequest(ro));
    }


}
