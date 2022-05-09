package com.example;

import org.apache.pulsar.client.api.schema.GenericRecord;
import org.apache.pulsar.common.schema.KeyValue;
import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;
import org.slf4j.Logger;

public class StdoutFunction implements Function<KeyValue<GenericRecord, GenericRecord>, Void> {
    @Override
    public Void process(KeyValue<GenericRecord, GenericRecord> input, Context context) {
        Logger LOG = context.getLogger();
        System.out.println(input);
        LOG.info("{}", input);

        return null;
    }
}
