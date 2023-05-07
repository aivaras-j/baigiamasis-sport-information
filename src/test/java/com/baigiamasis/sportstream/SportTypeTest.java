package com.baigiamasis.sportstream;

import com.baigiamasis.sportstream.event.Event;
import com.baigiamasis.sportstream.sportType.SportType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class SportTypeTest {

    @Test
    void testEventCreation() {
        Event event = new Event();
        assertNotNull(event);
    }
    @Test
    void testSetNameAndGetNam() {
        Event event = new Event();
        event.setName("Test event");
        assertEquals("Test event", event.getName());
    }
    @Test
    void testSetTeam1AndGetTeam1() {
        Event event = new Event();
        event.setTeam1("Test team 1");
        assertEquals("Test team 1", event.getTeam1());
    }
    @Test
    void testSetTeam2AndGetTeam2() {
        Event event = new Event();
        event.setTeam2("Test team 2");
        assertEquals("Test team 2", event.getTeam2());
    }
    @Test
    void testSetPlaceAndGetPlace() {
        Event event = new Event();
        event.setPlace("Test place");
        assertEquals("Test place", event.getPlace());
    }
    @Test
    void testSetSportTypeAndGetSportType() {
        Event event = new Event();
        SportType sportType = new SportType();
        sportType.setName("Test sport type");
        event.setSportType(sportType);
        assertEquals(sportType, event.getSportType());
    }
}
