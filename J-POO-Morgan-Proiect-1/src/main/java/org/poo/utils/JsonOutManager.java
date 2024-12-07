package org.poo.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public final class JsonOutManager {
    private static JsonOutManager instance = null;
    private ArrayNode output;

    private JsonOutManager() {
    }
    public static JsonOutManager getInstance() {
        if (instance == null) {
            instance = new JsonOutManager();
        }
        return instance;
    }
    public ArrayNode getOutput() {
        return output;
    }
    public void setOutput(ArrayNode output) {
        this.output = output;
    }
    public void addToOutput(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(object);
        this.output.add(node);
    }
}
