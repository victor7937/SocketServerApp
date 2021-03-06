package by.victor.jwd.parser.function;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;
import by.victor.jwd.dao.util.PropertyLoader;
import by.victor.jwd.parser.util.TextFormatter;

import java.util.List;
import java.util.regex.Pattern;

public class GreedyDeleteFunction implements RequestFunction {

    private final static String SOME_SYMBOLS_BETWEEN_PATTERN = PropertyLoader.loadProperty("patterns.xml","some_symbols_between");
    private final static String SPACES_PATTERN = PropertyLoader.loadProperty("patterns.xml","spaces");

    @Override
    public String apply(Text textObject, String params) {
        String[] symbols = params.split(SPACES_PATTERN);
        List<TextFragment> sentences = textObject.getFragmentsForm();
        StringBuilder resultSentencesBuilder = new StringBuilder();

        for (TextFragment sentence : sentences){
            resultSentencesBuilder.append(Pattern.compile(symbols[0] + SOME_SYMBOLS_BETWEEN_PATTERN + symbols[1])
                    .matcher(sentence.getTextForm()).replaceFirst("")).append(" ");
        }

        return TextFormatter.textAlignment(resultSentencesBuilder.toString());
    }
}
