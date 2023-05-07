package com.baigiamasis.sportstream.event;

import com.baigiamasis.sportstream.sportType.SportType;
import com.baigiamasis.sportstream.sportType.SportTypeRepository;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class EventController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SportTypeRepository sportTypeRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    public EventController(@Autowired EventRepository repository) {

        this.eventRepository = repository;
    }

    @PostMapping("/news/sportType/{id}/newEvent")
    public ResponseEntity<Event> createNewEvent(@PathVariable("id") int id, @Valid @RequestBody Event event, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<SportType> optionalSportType = sportTypeRepository.findById(id);
        if (optionalSportType.isPresent()) {
            SportType sportType = optionalSportType.get();
            event.setSportType(sportType);
            eventRepository.save(event);

            logger.info("New event: {}", event);

            return new ResponseEntity<>(event, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/news/sportType/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable int id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return new ResponseEntity<>("Event deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }
    }

}
