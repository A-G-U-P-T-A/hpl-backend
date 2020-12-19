package com.hpl.restservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service public class ObjectMapperService {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
