package com.baigiamasis.sportstream.sportType;

import com.baigiamasis.sportstream.event.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportTypeService {

    @Autowired
    SportTypeRepository sportTypeRepository;

    public SportType get(String name) {
        SportType sportType = sportTypeRepository.findByName(name);
        return sportType;
    }

    public SportType getSportTypegById(Integer id) {

        return sportTypeRepository.findById(id).get();
    }

    public List<SportType> getAllSportTypes() {

        return sportTypeRepository.findAll();
    }

    public SportType createAndEditSportType(SportType sportType) {

        return sportTypeRepository.save(sportType);
    }

    public SportType saveEvents(int id, List<Event> events) {
        SportType sportType = getSportTypegById(id);
        sportType.setEvents(events);
        createAndEditSportType(sportType);
        return sportType;
    }
}
