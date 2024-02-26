package com.example.WeatherApp.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.WeatherApp.Connector.WeatherApi;
import com.example.WeatherApp.Entity.WeatherEntity;

@Component
public class WeatherCron {

    @Autowired
    private WeatherApi weatherApi;


    
    @Scheduled(fixedRate = 3600000)
    public WeatherEntity updateWeather(){
        return weatherApi.weatherApiUpdate();
    }
}
