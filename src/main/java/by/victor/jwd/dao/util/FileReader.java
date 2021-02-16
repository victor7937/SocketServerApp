package by.victor.jwd.dao.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class FileReader {

    private static final Logger logger = Logger.getLogger(FileReader.class);

    public static String readFile (String fileName) {
        boolean fileErrFlag = false;
        StringBuilder fileStringFormatBuilder = new StringBuilder();
        Stream<String> lines = null;
        try {
            URL url = ClassLoader.getSystemResource(fileName);
            if (url != null) {
                lines = Files.lines(Paths.get(url.toURI()));
                lines.forEach(line -> fileStringFormatBuilder.append(line.strip()).append(" "));
            }
        } catch ( URISyntaxException | IOException e) {
            fileErrFlag = true;
            logger.error("Exception while loading text from file " + fileName + " : " + e.toString());
        } finally {
            if (lines != null) {
                lines.close();
            }
        }
        return fileErrFlag ? "" : fileStringFormatBuilder.toString();
    }
}
