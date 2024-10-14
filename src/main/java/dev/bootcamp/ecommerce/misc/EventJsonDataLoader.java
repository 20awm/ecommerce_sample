package dev.bootcamp.ecommerce.misc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;

@Component
public class EventJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(EventJsonDataLoader.class);
    private final JdbcClientEventRepository eventRepository;
    private final ObjectMapper objectMapper;

    public EventJsonDataLoader(JdbcClientEventRepository eventRepository, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(eventRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/events.json")) {
                Events allEvents = objectMapper.readValue(inputStream, Events.class);
                log.info("Reading {} events from JSON data and save to database", allEvents.events().size());
                eventRepository.saveAll(allEvents.events());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read from .json file", e);
            }
        } else {
            log.info("Not loading Events from JSON data because collection contains data");
        }
    }
}
