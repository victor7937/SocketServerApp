package by.victor.jwd.parser;

import by.victor.jwd.entity.*;
import by.victor.jwd.parser.functions.SameWordsFunction;
import by.victor.jwd.parser.functions.SortSentencesByWordsCount;

import java.text.BreakIterator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private static Map<Integer,RequestFunction> functionsMap = new HashMap<>();

    static {
        functionsMap.put(1, new SameWordsFunction());
        functionsMap.put(2, new SortSentencesByWordsCount());
    }

    public static String parseByRequest (RequestObject requestObject, String text){
        if (requestObject.getTaskId()  < 0 || requestObject.getTaskId() > 2) {
            return "";
        }
        Text textObject = createTextObject(text);
        return functionsMap.get(requestObject.getTaskId()).apply(textObject, requestObject.getTaskParam());
    }

    private static Text createTextObject(String textSource){
        StringBuilder textBuilder = new StringBuilder();
        List<String> codeBlocks = splitCodeAndText(textSource,textBuilder);
        Text textObject = new Text(textBuilder.toString(), codeBlocks);

        BreakIterator boundary = BreakIterator.getSentenceInstance(Locale.US);
        boundary.setText(textBuilder.toString());
        List<String> sentList = splitByFragments(boundary, textBuilder.toString());

        for (String sentence : sentList) {
            boundary = BreakIterator.getWordInstance(Locale.US);
            boundary.setText(sentence);
            List<String> wordsList = splitByFragments(boundary,sentence,"[a-zA-Z-]+");
            Sentence sentenceObject = new Sentence(sentence);
            for (String word : wordsList) {
                TextFragment wordObject = new Word(word, Arrays.asList(word.split("")));
                sentenceObject.addWord(wordObject);
            }
            textObject.addSentence(sentenceObject);
        }
        return textObject;
    }

    private static List<String> splitCodeAndText(String text, StringBuilder textBuilder){
        List<String> codeBlocks = new LinkedList<>();
        Pattern pattern = Pattern.compile("-{3,}");
        Matcher matcher = pattern.matcher(text);
        int textStart = 0;
        while (matcher.find()){
            int codeStart = matcher.end();
            int textEnds = matcher.start();
            if (matcher.find()){
                codeBlocks.add(text.substring(codeStart, matcher.start()));
                textBuilder.append(text, textStart, textEnds);
                textStart = matcher.end();
            }
        }
        textBuilder.append(text, textStart, text.length());
        return codeBlocks;
    }
    private static List<String> splitByFragments(BreakIterator boundary, String source) {
        int start = boundary.first();
        List<String> fragmentsList = new LinkedList<>();
        for (int end = boundary.next();
             end != BreakIterator.DONE;
             start = end, end = boundary.next()) {
            fragmentsList.add(source.substring(start,end));
        }
        return fragmentsList;
    }

    private static List<String> splitByFragments(BreakIterator boundary, String source, String fragmentPattern) {
        int start = boundary.first();
        List<String> fragmentsList = new LinkedList<>();
        for (int end = boundary.next();
             end != BreakIterator.DONE;
             start = end, end = boundary.next()) {
            String fragment = source.substring(start,end);
            if (fragment.matches(fragmentPattern)){
                fragmentsList.add(fragment);
            }
        }
        return fragmentsList;
    }

}
