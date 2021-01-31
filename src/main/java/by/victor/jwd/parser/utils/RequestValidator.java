package by.victor.jwd.parser.utils;

import by.victor.jwd.dao.utils.PropertyLoader;
import by.victor.jwd.entity.RequestObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class RequestValidator {

    private static final String NUMBERS_PATTERN = "\\d+";
    private static final String LETTER_PATTERN = "[a-zA-Z]";
    private static final String SYMBOL_PATTERN = "(^[^.\\s,]+$)";
    private static final String TWO_SYMBOLS_PATTERN = "[.[^\\s]]\\s+[.[^\\s]]";
    private static final String PARAMS16_PATTERN = PropertyLoader.loadProperty("patterns.xml","params16");
    private static final Map<Integer, Function<RequestObject, Boolean>> validatorsMap = new HashMap<>();

    static {
        validatorsMap.put(4, ro -> ro.getTaskParam().matches(NUMBERS_PATTERN));
        validatorsMap.put(9, ro -> ro.getTaskParam().matches(LETTER_PATTERN));
        validatorsMap.put(10, ro -> !ro.getTaskParam().isEmpty());
        validatorsMap.put(11, ro -> ro.getTaskParam().matches(TWO_SYMBOLS_PATTERN));
        validatorsMap.put(12, ro -> ro.getTaskParam().matches(NUMBERS_PATTERN));
        validatorsMap.put(13, ro -> ro.getTaskParam().matches(SYMBOL_PATTERN));
        validatorsMap.put(16, ro -> ro.getTaskParam().matches(PARAMS16_PATTERN));
    }

    public static boolean validate (RequestObject requestObject){
        int taskId = requestObject.getTaskId();
        if (taskId  < 0 || taskId > 16) {
            return false;
        }
        if (validatorsMap.containsKey(taskId)){
            return validatorsMap.get(taskId).apply(requestObject);
        }
        return true;
    }
}
