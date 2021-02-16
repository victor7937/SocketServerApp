package by.victor.jwd.parser.function;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;

import java.util.Comparator;
import java.util.List;

public class SortAllWordsAlphabeticallyFunction implements RequestFunction {

    @Override
    public String apply(Text textObject, String params) {
        List<TextFragment> words = textObject.getWordsList();
        words.sort(Comparator.comparing(TextFragment::getTextForm));
        StringBuilder wordsStringBuilder = new StringBuilder();

        for (int i = 0; i < words.size(); i++) {
            if ( i != 0 && words.get(i).getTextForm().charAt(0) != words.get(i-1).getTextForm().charAt(0)){
                wordsStringBuilder.append("\n");
            }
            wordsStringBuilder.append(words.get(i).getTextForm()).append(" ");
        }

        return wordsStringBuilder.toString();
    }
}
