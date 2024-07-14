package com.agustin.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConvert implements IDataConvert {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> kind) {
        try {
            return objectMapper.readValue(json,kind);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
