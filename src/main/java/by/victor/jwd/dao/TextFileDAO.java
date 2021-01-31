package by.victor.jwd.dao;

import by.victor.jwd.dao.utils.FileReader;
import by.victor.jwd.server.Server;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;

public class TextFileDAO implements FileDAO {

    private static final String FILE_NAME = "text.txt";
    private static final Logger logger = Logger.getLogger(TextFileDAO.class);
    private static final TextFileDAO instance = new TextFileDAO();

    private TextFileDAO() {}

    public static TextFileDAO getInstance() { return instance; }

    @Override
    public String loadTextString() {
        String text = "";
        try {
            text = FileReader.readFile(FILE_NAME);
        } catch (URISyntaxException | IOException e) {
            logger.error("Text file reading exception: " + e.toString());
        }
        return text;
    }
}
