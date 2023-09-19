package org.codechallenges.jsonparser;

import org.junit.Assert;
import org.junit.Test;

public class JSONParserTest {

    @Test
    public void validJSONBlankObject() {
        JSONParser jsonParser = new JSONParser();

        Assert.assertTrue(jsonParser.isValidJson("{}"));
    }

    @Test
    public void invalidJSONObject() {
        JSONParser jsonParser = new JSONParser();

        Assert.assertFalse(jsonParser.isValidJson("}"));
    }

    @Test
    public void validJSONStringKeyValues() {
        JSONParser jsonParser = new JSONParser();

        Assert.assertTrue(jsonParser.isValidJson("{\"key1\" : \"value1\", \"key2\" : \"value2\", \"key3\" : \"value1\", \"key4\" : \"value2\"}"));
    }
    @Test
    public void invalidJSONStringKeyValues() {
        JSONParser jsonParser = new JSONParser();

        Assert.assertFalse(jsonParser.isValidJson("{\"key1\" , \"key2\" : \"value2\", \"key3\" : \"value1\", \"key4\" : \"value2\"}"));
        Assert.assertFalse(jsonParser.isValidJson("{\"key1\" : \"value1\", \"key2\" : \"value2\", \"key3\" : \"value1\", \"key4\" : \"value2\",}"));
        Assert.assertFalse(jsonParser.isValidJson("{,}"));
        Assert.assertFalse(jsonParser.isValidJson("{\"key1\" : \"value1\", \"key2\" :}"));
        Assert.assertFalse(jsonParser.isValidJson("{\"key1\" : \"value1\" \"key2\" :}"));
    }

}
