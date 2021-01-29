package by.victor.jwd.parser.functions;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByVowelsRatioFunction implements RequestFunction {

    private static final String vowels = "aeiouAEIOU";

    @Override
    public String apply(Text textObject, String params) {
        List<TextFragment> words = textObject.getWordsList();
        words.sort(Comparator.comparingDouble(SortByVowelsRatioFunction::countVowelsRatio));
        return words.stream().map(word -> word.getTextForm() + "\n").collect(Collectors.joining());
    }

    private static Double countVowelsRatio (TextFragment word){
        int vowelsCount = 0;
        for (char letter : word.getTextForm().toCharArray()){
            if (vowels.indexOf(letter) >= 0){
                vowelsCount++;
            }
        }
        return (double) vowelsCount / word.fragmentsCount();
    }
}
