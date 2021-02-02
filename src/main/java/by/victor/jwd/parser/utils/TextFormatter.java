package by.victor.jwd.parser.utils;

import by.victor.jwd.dao.utils.PropertyLoader;

public class TextFormatter {

    private final static String HELP_MSG_PATTERN = PropertyLoader.loadProperty("patterns.xml","help");
    private final static String HELP_MSG_REPLACEMENT = "$1\n$2";
    private final static String MORE_THAN_TWO_SPACES_PATTERN = PropertyLoader.loadProperty("patterns.xml","spaces_two_plus");
    private final static int LINE_MAX_LENGTH = 100;

    public static String helpMsgFormat(String text){
        return text.replaceAll(HELP_MSG_PATTERN, HELP_MSG_REPLACEMENT);
    }

    public static String textAlignment(String text){
        StringBuilder textBuilder = new StringBuilder(text
                .replaceAll(MORE_THAN_TWO_SPACES_PATTERN," "));
        int currentPosition = LINE_MAX_LENGTH;
        while (currentPosition < textBuilder.length() - 1){
            while (textBuilder.charAt(currentPosition) != ' '){
                currentPosition--;
            }
            textBuilder.setCharAt(currentPosition,'\n');
            currentPosition += LINE_MAX_LENGTH;
        }
        return textBuilder.toString();
    }
}

