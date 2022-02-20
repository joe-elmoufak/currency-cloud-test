package utils;

import org.json.JSONObject;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelperFunction {

    public static final Map<String, Integer> EXPECTED_RESPONSE = Stream.of(new Object[][] {
            { "success", 200 },
            { "bad_request", 400 },
            { "unauthorised", 401 },
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

    public Object getValuefor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }
}
