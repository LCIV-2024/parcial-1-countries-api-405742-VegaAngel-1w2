package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Map;

public class CountryEntity {

    private String name;
    private long population;
    private double area;
    private String code;
    private String region;
    private List<String> borders;
    private Map<String, String> languages;
}
