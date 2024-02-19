package com.example.WeatherApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WeatherApp.Service.WeatherService;




@RestController
@RequestMapping("/weather")
public class WeatherController {


    @Autowired
    private WeatherService weatherService;
    
    @GetMapping
    public ResponseEntity getWeather(){
        try{
            return ResponseEntity.ok(weatherService.getWeather());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


}
