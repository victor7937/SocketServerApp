package by.victor.jwd.parser.functions;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortBySymbolQuantityReversedFunction implements RequestFunction {

    @Override
    public String apply(Text textObject, String params) {
        List<TextFragment> words = textObject.getWordsList();
        words.sort(Comparator.comparingLong((TextFragment w) -> w.toStringList().stream()
                .filter(symbol -> symbol.equals(params)).count()).reversed().thenComparing(TextFragment::getTextForm));

        return words.stream().map(word -> word.getTextForm() + "\n").collect(Collectors.joining());
    }
}
