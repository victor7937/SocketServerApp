package by.victor.jwd.parser.function;

import by.victor.jwd.entity.Text;
import by.victor.jwd.entity.TextFragment;
import by.victor.jwd.parser.RequestFunction;
import by.victor.jwd.dao.util.PropertyLoader;
import by.victor.jwd.parser.util.TextFormatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DeleteFirstAndLastLetterSymbolsFunction implements RequestFunction {

    private static final String AFTER_WORD_PATTERN = PropertyLoader.loadProperty("patterns.xml","after_word");
    private static final String BEFORE_WORD_PATTERN = PropertyLoader.loadProperty("patterns.xml","before_word");

    @Override
    public String apply(Text textObject, String params) {

        List<TextFragment> words = textObject.getWordsList();
        Map<String, String> modifiedWordsMap = new HashMap<>();
        for (TextFragment word : words){
            modifiedWordsMap.put(word.getTextForm(), modifyWord(word));
        }

        String fullText = textObject.getTextForm();
        for (String key : modifiedWordsMap.keySet()) {
            fullText = Pattern.compile(BEFORE_WORD_PATTERN + "(" + key + ")" + AFTER_WORD_PATTERN).matcher(fullText)
                    .replaceAll("$1" + modifiedWordsMap.get(key) + "$3");
        }

        return TextFormatter.textAlignment(fullText);
    }

    private static String modifyWord (TextFragment word){
        if (word.fragmentsCount() < 2) {
            return word.getTextForm();
        }
        List<String> symbols = word.toStringList();
        String firstLetter = symbols.get(0);
        String lastLetter = symbols.get(symbols.size() - 1);
        return firstLetter + symbols.stream().filter(symbol -> !(symbol.equals(firstLetter)))
                .filter(symbol -> !(symbol.equals(lastLetter))).collect(Collectors.joining()) + lastLetter;
    }
}
