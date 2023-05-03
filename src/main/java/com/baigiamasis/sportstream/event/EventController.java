package com.baigiamasis.sportstream.event;

import com.baigiamasis.sportstream.sportType.SportType;
import com.baigiamasis.sportstream.sportType.SportTypeRepository;
import com.baigiamasis.sportstream.sportType.SportTypeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class EventController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SportTypeRepository sportTypeRepository;

    @Autowired
    SportTypeService sportTypeService;

    @Autowired
    EventService eventService;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    public EventController(@Autowired EventRepository repository) {

        this.eventRepository = repository;
    }

    @GetMapping("/news/sportType/{id}/newEvent")
    public String newEventForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("sportType", sportTypeService.getSportTypegById(id));
        var events = eventRepository.findAllBySportTypeId(id);

        model.addAttribute("events", events);
        model.addAttribute("event", new Event());
        return "event/newEvent";
    }

    @PostMapping("/news/sportType/newEvent")
    public ResponseEntity<Event> createNewEvent(@PathVariable("id") Integer id, @RequestBody @Valid Event event, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            logger.info("Yep, I have errors: {}", errors.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (event.getSportType() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        logger.info("New event: {}", event);

        Optional<SportType> optionalSportType = sportTypeRepository.findById(id);
        if (optionalSportType.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SportType sportType = optionalSportType.get();

        event.setSportType(sportType);
        eventRepository.save(event);

        return ResponseEntity.ok(event);
    }
}

//    @PostMapping("/news/event/newEvent")
//    public String createNewEvent(@PathVariable("id") Integer id, @Valid Event event, BindingResult errors, Model model) {
//        if (errors.hasErrors()) {
//            logger.info("Yep, I have error: {}", event);
//            errors.getAllErrors().forEach(System.out::println);
//            return "event/newEvent";
//        }
//        logger.info("Event new: {}", event);
//
//        Optional<SportType> optionalSportType = sportTypeRepository.findById(id);
//        if (optionalSportType.isEmpty()) {
//            return "redirect:/news";
//        }
//
//        SportType sportType = optionalSportType.get();
//
//        event.setSportType(sportType);
//
//        eventRepository.save(event);
//
//        return "redirect:/news/sportType/{id}";
//    }




//    @PostMapping("/news/sportType/{id}/newEvent")
//    public String createNewEvent(@PathVariable("id") Integer id, @Valid Event event, BindingResult errors, Model model) {
//        if (errors.hasErrors()) {
//            logger.info("Yep, I have error: {}", event);
//            errors.getAllErrors().forEach(System.out::println);
//            return "event/newEvent";
//        }
//        logger.info("Event new: {}", event);
//
//        SportType sportType = sportTypeService.getSportTypegById(id);
//
//        if (sportType == null) {
//            return "redirect:/news";
//        }
//
//        event.setSportType(sportType);
//
//        eventRepository.save(event);
//
//
//        var allEvents = eventRepository.findAll();
//        var sportsByType = sportTypeRepository.findById(id);
//
//        logger.info("allEvents {}", allEvents);
//        logger.info("sportsByType {}", sportsByType);
//
//
//        return "redirect:/news/sportType/{id}";
//    }

