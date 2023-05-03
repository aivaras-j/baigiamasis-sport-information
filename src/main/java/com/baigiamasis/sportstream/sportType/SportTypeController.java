package com.baigiamasis.sportstream.sportType;

import com.baigiamasis.sportstream.article.Article;
import com.baigiamasis.sportstream.article.Articles;
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

import java.util.List;
import java.util.Optional;

@Controller
public class SportTypeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    SportTypeRepository sportTypeRepository;

    @Autowired
    SportTypeService sportTypeService;

    @Autowired
    public SportTypeController(@Autowired SportTypeRepository repository) {

        this.sportTypeRepository = repository;
    }

    @GetMapping("/news/sportType/{id}")
    public ResponseEntity<SportType> sportTypeById(@PathVariable int id) {
        Optional<SportType> sportType = sportTypeRepository.findById(id);
        if (sportType.isPresent()) {
            return new ResponseEntity<>(sportType.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


//    @GetMapping("/news/sportType/{id}")
//    public String sportType(@PathVariable("id") int id, Model model) {
//        model.addAttribute("sportType", sportTypeService.getSportTypegById(id));
//        return "event/events";
//    }

    @GetMapping("/news/sportTypes")
    public ResponseEntity<SportTypes> sportTypes() {
        return new ResponseEntity<>(
                new SportTypes(sportTypeRepository.findAll()),
                HttpStatus.OK
        );
    }

//    @GetMapping("/news/sportTypes")
//    public String sportTypes(Model model) {
//        List<SportType> sportTypes = sportTypeService.getAllSportTypes();
//        model.addAttribute("sportTypes", sportTypes);
//        logger.info("sporttypes: {}", sportTypes);
//        return "event/sportEvents";
//    }

    @PostMapping("news/sportType/new")
    public ResponseEntity<SportType> createNewSportType(@Valid @RequestBody SportType sportType) {
        SportType created = sportTypeRepository.save(sportType);
        logger.info("New sportType: {}", sportType);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }






    @GetMapping("/news/sportTypes/new")
    public String createNewSportType(Model model) {
        model.addAttribute("sportType", new SportType());
        return "event/newSportType";
    }

    @PostMapping("/news/sportTypes/new")
    public String createNewSportType(@Valid SportType sportType, BindingResult errors, Model model) {
        if (errors.hasErrors())
            return "news/newSportType";

        SportType created = sportTypeService.createAndEditSportType(sportType);
        model.addAttribute("sportType", created);

        logger.info("New article: {}", sportType);

        return "redirect:/news/sportType/" + created.getId();
    }


}
