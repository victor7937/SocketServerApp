package by.victor.jwd.client;

import by.victor.jwd.entity.RequestObject;
import java.io.*;
import java.net.Socket;


public class Client {

    private static final int port = 4040;
    private static final String hostName = "localhost";


    public Client () {}

    public String sendRequest (RequestObject requestObject){
        String request = "";
        try ( Socket socket = new Socket(hostName, port);
              ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
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
        RequestObject ro = new RequestObject(16, "3, 5, ♂gay website♂");
        System.out.println(client.sendRequest(ro));
    }
}
