package com.example.WeatherApp.Entity;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String main;
    private String description;
    private Float temp;
    private Float feelsLike;
    private String city;


    public String getMain() {
        return main;
    }


    public void setMain(String main) {
        this.main = main;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Float getTemp() {
        return temp;
    }


    public void setTemp(Float temp) {
        this.temp = temp;
    }


    public Float getFeelsLike() {
        return feelsLike;
    }


    public void setFeelsLike(Float feelsLike) {
        this.feelsLike = feelsLike;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public WeatherEntity(){}
    
}
