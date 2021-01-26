package by.victor.jwd.server;

import by.victor.jwd.entity.RequestObject;
import by.victor.jwd.server.utils.FileReader;
import by.victor.jwd.parser.TextParser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

public class Server extends Thread {
    private static final int port = 4040;
    private ServerSocket serverSocket;
    private boolean exitFlag = true;
    private static final String fileName = "text.txt";

    public Server()  {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (exitFlag) {
            try (Socket clientSocket = serverSocket.accept(); ObjectInputStream objectInputStream =
                    new ObjectInputStream(clientSocket.getInputStream()); ObjectOutputStream objectOutputStream =
                         new ObjectOutputStream(clientSocket.getOutputStream())) {

                RequestObject requestObject = (RequestObject) objectInputStream.readObject();
                if (requestObject.getTaskId() == 0) {
                    objectOutputStream.writeUTF("Goodbye");
                    objectOutputStream.flush();
                    exitFlag = false;
                }
                else {
                    objectOutputStream.writeUTF(TextParser.parseByRequest(requestObject, getTextFromFile()));
                    objectOutputStream.flush();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getTextFromFile (){
        String text = "";
        try {
            text = FileReader.readFile(fileName);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void main(String[] args) {
        Thread server = new Server();
        server.start();
    }
}
