package dev.bootcamp.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String title;

    private LocalDateTime startOn;

    private LocalDateTime completeOn;

    @Positive
    private Integer participant;

    private String location;

    @Version
    private Integer version;

    // Default constructor
    public Event() {
    }

    // Parameterized constructor
    public Event(
            Integer id,
            String title,
            LocalDateTime startOn,
            LocalDateTime completeOn,
            Integer participant, String location, Integer version) {
        if (!completeOn.isAfter(startOn)) {
            throw new IllegalArgumentException("Complete must be after start");
        }
        this.id = id;
        this.title = title;
        this.startOn = startOn;
        this.completeOn = completeOn;
        this.participant = participant;
        this.location = location;
        this.version = version;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartOn() {
        return startOn;
    }

    public void setStartOn(LocalDateTime startOn) {
        this.startOn = startOn;
    }

    public LocalDateTime getCompleteOn() {
        return completeOn;
    }

    public void setCompleteOn(LocalDateTime completeOn) {
        this.completeOn = completeOn;
    }

    public Integer getParticipant() {
        return participant;
    }

    public void setParticipant(Integer participant) {
        this.participant = participant;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}