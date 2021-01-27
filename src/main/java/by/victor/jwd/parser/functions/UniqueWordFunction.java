package by.victor.jwd.parser.functions;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;

import java.util.List;

public class UniqueWordFunction implements RequestFunction {

    public UniqueWordFunction() {}

    private final static int FIRST_SENTENCE_INDEX = 0;

    @Override
    public String apply(Text textObject, String params) {
        List<TextFragment> sentences = textObject.getFragmentsForm();
        TextFragment firstSentence = sentences.get(FIRST_SENTENCE_INDEX);
        sentences.remove(firstSentence);

        for (TextFragment word : firstSentence.getFragmentsForm()){
            boolean wordFoundFlag = false;
            for (TextFragment sentence : sentences){
               if (sentence.getFragmentsForm().contains(word)){
                   wordFoundFlag = true;
                   break;
               }
            }
            if(!wordFoundFlag){
                return word.getTextForm();
            }
        }
        return "No such words";
    }
}
