package com.baigiamasis.sportstream.sportType;

import com.baigiamasis.sportstream.event.Event;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "sport_types")
public class SportType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @NonNull
    @NotEmpty(message = "Name title cannot be empty")
    private String name;


    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "sportType", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    public SportType() {

    }

    public SportType(@NonNull String name) {
        this.name = name;
    }

    public void addEvent(Event event) {
        events.add(event);
        event.setSportType(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "SportType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", events=" + events +
                '}';
    }
}

