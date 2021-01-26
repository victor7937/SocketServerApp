package by.victor.jwd.parser.functions;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortSentencesByWordsCount implements RequestFunction {

    public SortSentencesByWordsCount () {}

    @Override
    public String apply(Text textObject, String params) {
        List<TextFragment> sentences = textObject.getFragmentsForm();
        sentences.sort(Comparator.comparingInt(TextFragment::fragmentsCount));
        return sentences.stream().map(sentence -> sentence.getTextForm() + "\n").collect(Collectors.joining());
    }
}
