package dev.bootcamp.ecommerce.service;

import dev.bootcamp.ecommerce.misc.EventNotFoundException;
import dev.bootcamp.ecommerce.repository.EventRepository;
import dev.bootcamp.ecommerce.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // GET ALL DATA
//    @GetMapping("")
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    // GET DATA BY ID
//    @GetMapping("/{id}")
    public Event findById(Integer id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    // POST
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    // PUT
    public void update(Integer id, Event event) {
        if(!eventRepository.existsById(id)) {
            throw new EventNotFoundException(id);
        }
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        Event updatedEvent = new Event(
                id,
                event.getTitle(),
                event.getStartOn(),
                event.getCompleteOn(),
                event.getParticipant(),
                event.getLocation(),
                existingEvent.getVersion()
        );
        eventRepository.save(updatedEvent);
    }
//
//    // DELETE
    public void delete(Integer id) {
        eventRepository.delete(
                eventRepository
                .findById(id)
                .orElseThrow(() -> new EventNotFoundException(id)));
    }
//

    public List<Event> findByLocationAndParticipant(
            String location,
            Integer participant) {

        if (location != null && participant != null) {
            return eventRepository.findAllByLocationAndParticipant(
                    location, participant);
        } else if (location != null) {
            return eventRepository.findAllByLocation(location);
        } else if (participant != null) {
            return eventRepository.findAllByParticipant(participant);
        } else {
            return eventRepository.findAll();
        }
    }
//
//    // FIND WITH PATH
    public List<Event> findByLocation(String location) {
        return eventRepository.findAllByLocation(location);
    }
}
