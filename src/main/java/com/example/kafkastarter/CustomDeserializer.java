package com.example.kafkastarter;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.ExtendedDeserializer;

import java.util.Map;

public class CustomDeserializer implements ExtendedDeserializer {
    @Override
    public Object deserialize(String s, Headers headers, byte[] bytes) {
        return null;
    }

    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        return null;
    }

    @Override
    public void close() {

    }
}
