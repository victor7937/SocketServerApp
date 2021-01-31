package by.victor.jwd.parser.functions;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;
import by.victor.jwd.dao.utils.PropertyLoader;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterrogativeFixedLengthWordsFunction implements RequestFunction {

    private final static String INTERROGATIVE_PATTERN = PropertyLoader.loadProperty("patterns.xml","interrogative");
    private int fixedLength;

    @Override
    public String apply(Text textObject, String params) {
        fixedLength = Integer.parseInt(params);
        List<TextFragment> sentences = textObject.getFragmentsForm();
        Pattern pattern = Pattern.compile(INTERROGATIVE_PATTERN);
        List<String> fixedLengthWords = new LinkedList<>();

        for (TextFragment sentence : sentences){
            Matcher matcher = pattern.matcher(sentence.getTextForm());
            if (matcher.find() && matcher.requireEnd()){
               addUniqueWords(sentence, fixedLengthWords);
            }
        }

        return fixedLengthWords.toString();
    }

    private void addUniqueWords(TextFragment sentence, List<String> fixedLengthWords) {
        for (TextFragment word : sentence.getFragmentsForm()) {
            if (word.fragmentsCount() == fixedLength && !fixedLengthWords.contains(word.getTextForm())) {
                fixedLengthWords.add(word.getTextForm());
            }
        }
    }

}
