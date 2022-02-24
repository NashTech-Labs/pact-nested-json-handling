package pact;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;

import static pact.ConsumerContract.objectMapper;

public class Utility {

    public static Map<String, Object> getStringObject(String attributeName)
            throws JsonProcessingException {
        return objectMapper.convertValue(
                objectMapper.readTree(attributeName), new TypeReference<Map<String, Object>>() {});
    }
}
