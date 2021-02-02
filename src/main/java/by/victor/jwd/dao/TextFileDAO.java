package by.victor.jwd.dao;

import by.victor.jwd.dao.utils.FileReader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;

public class TextFileDAO implements FileDAO {

    private static final String FILE_NAME_TEXT = "text.txt";
    private static final String FILE_NAME_HELPER = "helper.txt";
    private static final Logger logger = Logger.getLogger(TextFileDAO.class);
    private static final TextFileDAO instance = new TextFileDAO();

    private TextFileDAO() {}

    public static TextFileDAO getInstance() { return instance; }

    @Override
    public String loadTextString() {
        return loadFile(FILE_NAME_TEXT);
    }

    @Override
    public String loadHelperString() {
        return loadFile(FILE_NAME_HELPER);
    }

    private String loadFile (String filename) {
        String fileString = "";
        try {
            fileString = FileReader.readFile(filename);
        } catch (URISyntaxException | IOException e) {
            logger.error("Text file reading exception: " + e.toString());
        }
        return fileString;
    }
}
