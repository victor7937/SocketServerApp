package by.victor.jwd.parser.functions;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortBySymbolQuantityFunction implements RequestFunction {

    private static final int PARAM_LENGTH = 1;

    @Override
    public String apply(Text textObject, String params) {
        if (params.length() != PARAM_LENGTH)
            return "";

        List<TextFragment> words = textObject.getWordsList();
        words.sort(Comparator.comparingLong((TextFragment w) -> w.toStringList().stream()
                .filter(symbol -> symbol.equals(params)).count()).thenComparing(TextFragment::getTextForm));

        return words.stream().map(word -> word.getTextForm() + "\n").collect(Collectors.joining());
    }
}
