package by.victor.jwd.parser.functions;

import by.victor.jwd.entity.Text;
import by.victor.jwd.parser.RequestFunction;

public class SameWordsFunction implements RequestFunction {

    public SameWordsFunction () {}

    @Override
    public String apply(Text textObject, String params) {
        textObject.getCodeBlocks().forEach(System.out::println);
        return textObject.getTextForm().substring(0,20);
    }
}
