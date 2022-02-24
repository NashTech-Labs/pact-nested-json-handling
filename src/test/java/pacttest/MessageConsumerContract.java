package pacttest;

import static pact.ConsumerContract.*;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.junit.MessagePactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactFolder;
import au.com.dius.pact.core.model.messaging.MessagePact;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import pact.MessageConsumerConstant;
import pact.Utility;

@Tag("annotations")
@Tag("junit5")
@PactFolder("GeneratedPact")
public class MessageConsumerContract {

    public Map<String, Object> pact1 = new HashMap<>();
    @Rule
    public MessagePactProviderRule mockProvider = new MessagePactProviderRule(this);
    // for generating contract test
    @Pact(provider = "producer", consumer = "PersonIdFound")
    public MessagePact userCreatedMessagePact(MessagePactBuilder builder) throws IOException {
        MessagePact contract = contract(MessageConsumerConstant.Event, builder);
        pact1 = getStringObjectMap(contract);
        return contract;
    }

    @Test
    @PactVerification("userCreatedMessagePact")
    public void PersonIdFoundInBq() throws IOException {
        String body = pact1.get(MessageConsumerConstant.body).toString();
        Map<String, Object> pactBody = Utility.getStringObject(body);
        String personPact = pactBody.get(MessageConsumerConstant.person).toString();
        Map<String, Object> personId = Utility.getStringObject(personPact);
        Object personIdPact = personId.get(MessageConsumerConstant.personId);
        Assert.assertEquals(personIdPact,MessageConsumerConstant.personIdConstant);
    }

}
