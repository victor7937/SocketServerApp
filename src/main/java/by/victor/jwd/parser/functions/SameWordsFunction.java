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
       List<TextFragment> uniqueWords = textObject.getWordsList();
       List<TextFragment> sentences = textObject.getFragmentsForm();
       int sameWordsSentencesMaxCount = 0;

       for (TextFragment word : uniqueWords){
           int sameWordsSentencesCount = countSentencesHaveWord(sentences, word);
           if (sameWordsSentencesCount > sameWordsSentencesMaxCount) {
               sameWordsSentencesMaxCount = sameWordsSentencesCount;
           }
       }

       return Integer.toString(sameWordsSentencesMaxCount);

    }

    private static int countSentencesHaveWord (List<TextFragment> sentences, TextFragment word){
        int sameWordsSentencesCount = 0;
        for (TextFragment sentence : sentences) {
            if(sentence.getFragmentsForm().contains(word)){
                sameWordsSentencesCount++;
            }
        }
        return sameWordsSentencesCount;
    }


}
