package by.victor.jwd.parser.functions;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;
import by.victor.jwd.dao.utils.PropertyLoader;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DeleteGivenLengthWords implements RequestFunction {

    private static final String REPLACEMENT = "$1$3";
    private static final String VOWELS = PropertyLoader.loadProperty("patterns.xml","vowels");
    private static final String BEFORE_WORD_PATTERN = PropertyLoader.loadProperty("patterns.xml","before_word");
    private static final String AFTER_WORD_PATTERN = PropertyLoader.loadProperty("patterns.xml","after_word");

    @Override
    public String apply(Text textObject, String params) {
        int wordLengthGiven = Integer.parseInt(params);
        List<String> words = textObject.getWordsList().stream().map(TextFragment::getTextForm)
                .filter(word -> (word.length() == wordLengthGiven) && (VOWELS.indexOf(word.charAt(0)) < 0))
                .collect(Collectors.toList());

        String fullText = textObject.getTextForm();
        for (String word : words) {
            fullText = Pattern.compile(BEFORE_WORD_PATTERN + "(" + word + ")" + AFTER_WORD_PATTERN).matcher(fullText)
                    .replaceAll(REPLACEMENT);
        }

        return fullText;
    }
}
