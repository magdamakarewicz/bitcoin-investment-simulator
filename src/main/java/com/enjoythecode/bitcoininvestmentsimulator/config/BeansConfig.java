package com.enjoythecode.bitcoininvestmentsimulator.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * The BeansConfig class contains bean configuration methods for the Spring Framework application context.
 */
@Configuration
public class BeansConfig {

    /**
     * Creates an instance of the Jackson ObjectMapper with customized serialization and deserialization settings.
     *
     * @return An ObjectMapper object with the following configurations:
     * <p>
     * - SerializationFeature.WRITE_DATES_AS_TIMESTAMPS is set to false to serialize dates as ISO-8601 strings.
     * <p>
     * - SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS is set to false to serialize dates without nanosecond precision.
     * <p>
     * - DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES is set to false to ignore unknown properties during deserialization.
     * <p>
     * - A SimpleDateFormat object with the "dd-MM-yyyy" pattern is used to format dates.
     */
    @Bean
    public ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        return objectMapper;
    }

    /**
     * Creates an instance of the Executor interface with a fixed thread pool of size 5.
     *
     * @return An Executor object with a fixed thread pool of size 5.
     */
    @Bean
    public Executor createExecutor() {
        Executor executor = Executors.newFixedThreadPool(5);
        return executor;
    }

}
