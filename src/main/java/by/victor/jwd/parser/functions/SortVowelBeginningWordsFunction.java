package by.victor.jwd.parser.functions;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortVowelBeginningWordsFunction implements RequestFunction {

    private static final String vowels = "aeiouAEIOU";

    @Override
    public String apply(Text textObject, String params) {
        List<TextFragment> vowelBeginningWords = textObject.getWordsList().stream().filter(word ->
                vowels.indexOf(word.getTextForm().charAt(0)) >= 0).collect(Collectors.toList());

        vowelBeginningWords.sort(Comparator.comparing(TextFragment::getTextForm,
                Comparator.comparing(w -> w.replaceAll("[" + vowels + "]", ""))));

        return vowelBeginningWords.stream().map(word -> word.getTextForm() + "\n").collect(Collectors.joining());
    }
}
