package com.baigiamasis.sportstream.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Events {

    @NonNull
    private List<Event> events;

    public static Events of(List<Event> events) {
        return new Events(events);
    }
}
