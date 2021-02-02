package by.victor.jwd.parser.utils;

import by.victor.jwd.dao.utils.PropertyLoader;
import by.victor.jwd.entity.RequestObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class RequestValidator {

    private static final String NUMBER_PATTERN = PropertyLoader.loadProperty("patterns.xml","number");
    private static final String LETTER_PATTERN = PropertyLoader.loadProperty("patterns.xml","letter");
    private static final String PARAMS11_PATTERN = PropertyLoader.loadProperty("patterns.xml","params11");
    private static final String PARAMS13_PATTERN = PropertyLoader.loadProperty("patterns.xml","params13");
    private static final String PARAMS16_PATTERN = PropertyLoader.loadProperty("patterns.xml","params16");

    private static final Map<Integer, Function<RequestObject, Boolean>> validatorsMap = new HashMap<>();

    static {
        validatorsMap.put(4, ro -> ro.getTaskParam().matches(NUMBER_PATTERN));
        validatorsMap.put(9, ro -> ro.getTaskParam().matches(LETTER_PATTERN));
        validatorsMap.put(10, ro -> !ro.getTaskParam().isEmpty());
        validatorsMap.put(11, ro -> ro.getTaskParam().matches(PARAMS11_PATTERN));
        validatorsMap.put(12, ro -> ro.getTaskParam().matches(NUMBER_PATTERN));
        validatorsMap.put(13, ro -> ro.getTaskParam().matches(PARAMS13_PATTERN));
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
