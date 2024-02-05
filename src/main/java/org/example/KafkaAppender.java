package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.util.Properties;

@Plugin(name = "KafkaAppender", category = "Core", elementType = "appender", printObject = true)
public class KafkaAppender extends AbstractAppender {

    private final KafkaProducer<String, String> producer;
    private final String topic;

    protected KafkaAppender(String name, Layout<?> layout, String topic, KafkaProducer<String, String> producer) {
        super(name, null, layout, false);
        this.producer = producer;
        this.topic = topic;
    }

    @PluginFactory
    public static KafkaAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginAttribute("topic") String topic,
            @PluginAttribute("bootstrapServers") String bootstrapServers,
            @PluginAttribute(value = "Layout", defaultString = "%m%n") String layoutPattern) {

        Layout<?> layout = PatternLayout.newBuilder().withPattern(layoutPattern).build();
        KafkaProducer<String, String> producer = createKafkaProducer(bootstrapServers);
        return new KafkaAppender(name, layout, topic, producer);
    }

    @Override
    public void append(LogEvent event) {
        String message = new String(getLayout().toByteArray(event));
        producer.send(new ProducerRecord<>(topic, message));
    }

    private static KafkaProducer<String, String> createKafkaProducer(String bootstrapServers) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer<>(properties);
    }
}
