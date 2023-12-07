package io.arrogantprogrammer.domain;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hero {

    private String name;
    private String surname;
    private Double height;
    private Integer mass;
    private Boolean darkSide;
    private LightSaber lightSaber;
    private List<Integer> episodeIds = new ArrayList<>();

    public Hero(String name, String surname, Double height, Integer mass, Boolean darkSide, LightSaber lightSaber, List<Integer> episodeIds) {
        this.name = name;
        this.surname = surname;
        this.height = height;
        this.mass = mass;
        this.darkSide = darkSide;
        this.lightSaber = lightSaber;
        this.episodeIds = episodeIds;
    }

    public Hero() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public Boolean getDarkSide() {
        return darkSide;
    }

    public void setDarkSide(Boolean darkSide) {
        this.darkSide = darkSide;
    }

    public LightSaber getLightSaber() {
        return lightSaber;
    }

    public void setLightSaber(LightSaber lightSaber) {
        this.lightSaber = lightSaber;
    }

    public List<Integer> getEpisodeIds() {
        return episodeIds;
    }

    public void setEpisodeIds(List<Integer> episodeIds) {
        this.episodeIds = episodeIds;
    }
}