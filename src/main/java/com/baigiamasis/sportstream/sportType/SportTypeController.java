package com.baigiamasis.sportstream.sportType;

import com.baigiamasis.sportstream.event.Event;
import com.baigiamasis.sportstream.event.EventRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Controller
public class SportTypeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SportTypeRepository sportTypeRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    public SportTypeController(@Autowired SportTypeRepository repository) {

        this.sportTypeRepository = repository;
    }

    @GetMapping("/news/sportType/{id}")
    public ResponseEntity<SportTypeWithEvents> sportTypeWithEventsById(@PathVariable int id) {
        Optional<SportType> sportType = sportTypeRepository.findById(id);
        if (sportType.isPresent()) {
            List<Event> events = eventRepository.findBySportTypeId(id);
            SportTypeWithEvents sportTypeWithEvents = new SportTypeWithEvents(sportType.get(), events);
            sportTypeWithEvents.setId(id);
            return new ResponseEntity<>(sportTypeWithEvents, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/news/sportTypes")
    public ResponseEntity<SportTypes> sportTypes() {
        return new ResponseEntity<>(
                new SportTypes(sportTypeRepository.findAll()),
                HttpStatus.OK
        );
    }

    @PostMapping("news/sportType/new")
    public ResponseEntity<SportType> createNewSportType(@Valid @RequestBody SportType sportType) {
        SportType created = sportTypeRepository.save(sportType);
        logger.info("New sportType: {}", sportType);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

}
