package by.victor.jwd.client;

import by.victor.jwd.entity.RequestObject;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class Client {

    private static final int port = 4040;
    private static final String hostName = "localhost";
    private final static Logger logger = Logger.getLogger(Client.class);

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
            logger.error("Exception while interaction with the server: " + e.toString());
        }

        return request;
    }

    public String getHelpPage (){
        RequestObject ro = new RequestObject(0);
        return sendRequest(ro);
    }


    public static void main(String[] args) {
        Client client = new Client();
        System.out.println(client.getHelpPage());
        System.out.println("-------------------------------------");
        System.out.println(client.sendRequest(new RequestObject(16, "3, 5, java")));
        System.out.println("-------------------------------------");
        System.out.println(client.sendRequest(new RequestObject(15)));
        System.out.println("-------------------------------------");
        System.out.println(client.sendRequest(new RequestObject(10, "idea local names the")));
        System.out.println("-------------------------------------");
        System.out.println(client.sendRequest(new RequestObject(4, "5")));
        System.out.println("-------------------------------------");
        System.out.println(client.sendRequest(new RequestObject(5)));
        System.out.println("-------------------------------------");
        System.out.println(client.sendRequest(new RequestObject(11, "a s")));
    }
}
