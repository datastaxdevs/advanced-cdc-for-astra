package com.example;

import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;

public class TagsFunction implements Function<String, String> {
    @Override
    public String process(String input, Context context) {
        return String.format("%s!", input);
    }
}
