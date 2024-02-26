package com.example.WeatherApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WeatherApp.Connector.WeatherApi;
import com.example.WeatherApp.Entity.WeatherEntity;
import com.example.WeatherApp.Repository.WeatherRepo;


@Service
public class WeatherService {

    @Autowired
    private WeatherRepo weatherRepo;


    @Autowired
    private WeatherApi weatherApi;

    
    public WeatherEntity getWeather(String city){
        WeatherEntity weatherEntity = weatherRepo.findByCity(city);
        if(weatherEntity == null){
            weatherEntity = weatherApi.weatherApi(city);
        }
        return weatherEntity;
    }
}
