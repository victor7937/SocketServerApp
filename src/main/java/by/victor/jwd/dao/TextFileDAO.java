package by.victor.jwd.dao;

import by.victor.jwd.dao.util.FileReader;

public final class TextFileDAO implements FileDAO {

    private static final String FILE_NAME_TEXT = "text.txt";
    private static final String FILE_NAME_HELPER = "helper.txt";
    private static final TextFileDAO instance = new TextFileDAO();

    private TextFileDAO() {}

    public static TextFileDAO getInstance() { return instance; }

    @Override
    public String loadTextString() { return FileReader.readFile(FILE_NAME_TEXT); }

    @Override
    public String loadHelperString() { return FileReader.readFile(FILE_NAME_HELPER); }

}
