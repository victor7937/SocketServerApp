package by.victor.jwd.parser.functions;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;
import by.victor.jwd.parser.TextParser;
import by.victor.jwd.dao.utils.PropertyLoader;

import java.text.BreakIterator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SortGivenWordsByFrequency implements RequestFunction {

    private static final String WORD_PATTERN = PropertyLoader.loadProperty("patterns.xml","word");

    @Override
    public String apply(Text textObject, String params) {
        BreakIterator boundary = BreakIterator.getWordInstance(Locale.US);
        boundary.setText(params);
        List<String> givenWordsList = TextParser.splitByFragments(boundary, params, WORD_PATTERN);

        List<String> allWords = textObject.getFragmentsForm().stream().map(TextFragment::toStringList)
                .flatMap(List<String>::stream).collect(Collectors.toList());
        givenWordsList.sort(Comparator.comparingLong(w -> allWords.stream().filter(e -> e.equals(w)).count()).reversed());

        return givenWordsList.stream().map(word -> word + "\n").collect(Collectors.joining());

    }
}
