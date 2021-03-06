package by.victor.jwd.parser.function;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;
import by.victor.jwd.dao.util.PropertyLoader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SwapWordsWithStringGivenFunction implements RequestFunction {

    private static final String BEFORE_WORD_PATTERN = PropertyLoader.loadProperty("patterns.xml","before_word");
    private static final String AFTER_WORD_PATTERN = PropertyLoader.loadProperty("patterns.xml","after_word");
    private static final String PARAMS_PATTERN = PropertyLoader.loadProperty("patterns.xml","params16");

    @Override
    public String apply(Text textObject, String params) {
        Matcher matcher = Pattern.compile(PARAMS_PATTERN).matcher(params);
        matcher.find();
        int sentenceNumber = Integer.parseInt(matcher.group(1)) - 1;
        if (sentenceNumber >= textObject.fragmentsCount())
            return "";

        int wordLengthGiven = Integer.parseInt(matcher.group(2));
        String stringForReplace = matcher.group(3);

        TextFragment sentence = textObject.getFragmentsForm().get(sentenceNumber);
        List<String> words = sentence.toStringList().stream().filter(word -> (word.length() == wordLengthGiven))
                .collect(Collectors.toList());

        String sentenceString = " " + sentence.getTextForm();
        for (String word : words) {
            sentenceString = Pattern.compile(BEFORE_WORD_PATTERN + "(" + word + ")" + AFTER_WORD_PATTERN).matcher(sentenceString)
                    .replaceAll("$1" + stringForReplace + "$3");
        }

        return sentenceString;
    }
}
