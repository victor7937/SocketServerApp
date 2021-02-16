package by.victor.jwd.parser.function;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;
import by.victor.jwd.dao.util.PropertyLoader;
import by.victor.jwd.parser.util.TextFormatter;

import java.util.List;
import java.util.regex.Pattern;

public class SwapFirstWithLastFunction implements RequestFunction {

    private final static String MIDDLE_PATTERN = PropertyLoader.loadProperty("patterns.xml","middle_sentence");
    private final static String END_PATTERN = PropertyLoader.loadProperty("patterns.xml","end_sentence");
    private static final String REPLACEMENT_PATTERN = "$3$2$1$4";

    @Override
    public String apply(Text textObject, String params) {
        List<TextFragment> sentences = textObject.getFragmentsForm();
        StringBuilder textBuilder = new StringBuilder();

        for (TextFragment sentence : sentences) {
            List<TextFragment> words = sentence.getFragmentsForm();
            String firstWord = words.get(0).getTextForm();
            String lastWord = words.get(words.size() - 1).getTextForm();
            String changedSentence = Pattern.compile("("+firstWord+")" + MIDDLE_PATTERN + "("+lastWord+")" + END_PATTERN)
                    .matcher(sentence.getTextForm()).replaceAll(REPLACEMENT_PATTERN);
            textBuilder.append(changedSentence).append(" ");
        }

        return TextFormatter.textAlignment(textBuilder.toString());
    }
}
