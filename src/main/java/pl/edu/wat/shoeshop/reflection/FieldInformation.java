package pl.edu.wat.shoeshop.reflection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FieldInformation {

    private String fieldName1;
    private String fieldName2;
    private String fieldName3;
    private String fieldType1;
    private String fieldType2;
    private String fieldType3;

    public FieldInformation() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(new File("fields.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.fieldName1 = jsonNode.get("fieldName1").asText();
        this.fieldType1 = jsonNode.get("fieldType1").asText();
        this.fieldName2 = jsonNode.get("fieldName2").asText();
        this.fieldType2 = jsonNode.get("fieldType2").asText();
        this.fieldName3 = jsonNode.get("fieldName3").asText();
        this.fieldType3 = jsonNode.get("fieldType3").asText();

    }

    public String getFieldName1() {
        return fieldName1;
    }

    public String getFieldName2() {
        return fieldName2;
    }

    public String getFieldType1() {
        return fieldType1;
    }

    public String getFieldType2() {
        return fieldType2;
    }

    public String getFieldName3() {
        return fieldName3;
    }

    public String getFieldType3() {
        return fieldType3;
    }
}