package dev.bootcamp.ecommerce.controller;

import dev.bootcamp.ecommerce.model.Event;
import dev.bootcamp.ecommerce.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;

    }
    @GetMapping("")
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public Event findById(@PathVariable Integer id) {
        return eventService.findById(id);
    }

    @GetMapping("/location")
    public List<Event> findByLocationAndParticipant(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer participant
    ) {
        return eventService.findByLocationAndParticipant(
                location,participant);
    }


    @GetMapping("/location/{location}")
    public List<Event> findByLocation(@PathVariable String location) {
        return eventService.findByLocation(location);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Event create(@Valid @RequestBody Event event) {
        return eventService.create(event);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        eventService.delete(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Event event,
                       @PathVariable Integer id) {
        eventService.update(id, event);
    }
}
