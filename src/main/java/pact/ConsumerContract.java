package pact;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.core.model.messaging.MessagePact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConsumerContract {
    public static Map<String, Object> jsonMap, stringObjectMap = new HashMap<>();
    static ObjectMapper objectMapper = new ObjectMapper();

    public static MessagePact contract(String fileName, MessagePactBuilder builder) throws IOException {

        PactDslJsonBody body = new PactDslJsonBody();
        jsonMap = objectMapper.readValue(new File(fileName),
                new TypeReference<Map<String, Object>>() {
                });
        Map<String, Object> bodyAttribute =
                (Map<String, Object>) jsonMap.get(MessageConsumerConstant.body);
        for (Map.Entry<String, Object> entryJson : jsonMap.entrySet()) {
            if (entryJson.getKey().contains(MessageConsumerConstant.body)) {
                PactDslJsonBody bodyForPerson = new PactDslJsonBody();
                for (Map.Entry<String, Object> entryBody : bodyAttribute.entrySet()) {
                    if (entryBody.getKey().contains(MessageConsumerConstant.person)) {
                        PactDslJsonBody bodyForPersonId = new PactDslJsonBody();
                        for (Map.Entry<String, Object> entryPerson :
                                ((Map<String, Object>) bodyAttribute.get(MessageConsumerConstant.person))
                                        .entrySet()) {
                            bodyForPersonId.stringValue(entryPerson.getKey(), entryPerson.getValue().toString());
                        }
                        bodyForPerson.stringValue(entryBody.getKey(), bodyForPersonId.toString());
                    } else {
                        bodyForPerson.stringValue(entryBody.getKey(), entryBody.getValue().toString());
                    }
                    body.stringValue(entryJson.getKey(), bodyForPerson.toString());
                }
            } else {
                body.stringValue(entryJson.getKey(), entryJson.getValue().toString());
            }
        }
        MessagePact a_user_created_message =
                builder.expectsToReceive("a user created message").withContent(body).toPact();
        return a_user_created_message;
    }

    public static Map<String, Object> getStringObjectMap(MessagePact contract)
            throws JsonProcessingException {
        String pact = contract.getMessages().get(0).getContents().valueAsString();
        stringObjectMap =
                objectMapper.convertValue(
                        objectMapper.readTree(pact), new TypeReference<Map<String, Object>>() {});
        return stringObjectMap;
    }
}
