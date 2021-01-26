package by.victor.jwd.parser;

import by.victor.jwd.entity.Text;

@FunctionalInterface
public interface RequestFunction {
    String apply (Text textObject, String params);
}
