package com.baigiamasis.sportstream.sportType;

import com.baigiamasis.sportstream.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportTypeRepository extends JpaRepository<SportType, Integer> {

    //  Optional<SportType> findById(Integer id);
    SportType findByName(String name);

    List<Event> findEventsById(int id);
}


