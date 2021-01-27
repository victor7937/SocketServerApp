package by.victor.jwd.parser.functions;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;

import java.util.HashSet;
import java.util.List;

public class SameWordsFunction implements RequestFunction {

    public SameWordsFunction () {}

    @Override
    public String apply(Text textObject, String params) {
        List<TextFragment> sentences = textObject.getFragmentsForm();
        int sentenceCounter = 0;
        for (TextFragment sentence : sentences) {
            if (sentence.getFragmentsForm().size() != new HashSet<>(sentence.getFragmentsForm()).size()){
                sentenceCounter++;
            }
        }
        return Integer.toString(sentenceCounter);
    }


}
