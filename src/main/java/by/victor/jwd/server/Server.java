package by.victor.jwd.server;

import by.victor.jwd.dao.FileDAO;
import by.victor.jwd.dao.TextFileDAO;
import by.victor.jwd.entity.RequestObject;
import by.victor.jwd.parser.TextParser;
import by.victor.jwd.parser.utils.TextFormatter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private static final int port = 4040;
    private ServerSocket serverSocket;
    private boolean exitFlag = true;
    private static final Logger logger = Logger.getLogger(Server.class);

    public Server()  {
        try {
            serverSocket = new ServerSocket(port);
            logger.info("The server was created");
        } catch (IOException e) {
            logger.error("Exception while creating ServerSocket: " + e.toString());
            exitFlag = false;
        }
    }

    @Override
    public void run() {
        while (exitFlag) {
            try (Socket clientSocket = serverSocket.accept();
                 ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {

                RequestObject requestObject = (RequestObject) objectInputStream.readObject();
                logger.info("RequestObject had been received");
                FileDAO txtFileDAO = TextFileDAO.getInstance();
                if (requestObject.getTaskId() == 0) {
                    objectOutputStream.writeUTF(TextFormatter.helpMsgFormat(txtFileDAO.loadHelperString()));
                }
                else {
                    objectOutputStream.writeUTF(TextParser.parseByRequest(requestObject, txtFileDAO.loadTextString()));
                }
                objectOutputStream.flush();
                logger.info("Server text answer had been sent");

            } catch (IOException | ClassNotFoundException e) {
                logger.error("Exception while interaction with the client: " + e.toString());
            }
        }
        logger.info("The server is terminated");
    }

    public static void main(String[] args) {
        Thread server = new Server();
        server.start();
    }
}
