package com.baigiamasis.sportstream.sportType;

import com.baigiamasis.sportstream.event.Event;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SportTypeWithEvents extends SportType {

    private List<Event> events;

    public SportTypeWithEvents(SportType sportType, List<Event> events) {
        super(sportType.getName());
        this.setEvents(sportType.getEvents());
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public int getId() {
        return super.getId();
    }
}