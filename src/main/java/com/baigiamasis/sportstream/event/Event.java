package com.baigiamasis.sportstream.event;

import com.baigiamasis.sportstream.sportType.SportType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String place;

    @NotNull
    @NotBlank
    private String team1;

    @NotNull
    @NotBlank
    private String team2;

    @NonNull
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sport_type_id", nullable = false)
    private SportType sportType;


    public Event() {

    }

    public Event(String name, String team1, String team2, String place) {
        this.name = name;
        this.team1 = team1;
        this.team2 = team2;
        this.place = place;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public SportType getSportType() {
        return sportType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
