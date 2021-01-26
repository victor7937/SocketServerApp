package by.victor.jwd.server.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

    public static String readFile (String fileName) throws IOException, URISyntaxException {
        StringBuilder fileStringFormatBuilder = new StringBuilder();
        try(Stream<String> lines = Files.lines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))){
            lines.forEach(line -> fileStringFormatBuilder.append(line).append(" "));
        }
        return fileStringFormatBuilder.toString();
    }
}
