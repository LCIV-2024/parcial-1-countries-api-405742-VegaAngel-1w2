package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Entity
@Data
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private long population;
    private double area;
    private String code;
    private String region;
    //   private List<String> borders;
   // private Map<String, String> languages;
}
