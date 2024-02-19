package com.example.WeatherApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.IOException;

import com.example.WeatherApp.Repository.WeatherRepo;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepo weatherRepo;

    
    public String getWeather(){
        String baseUrl = "https://api.openweathermap.org/data/2.5/weather";
        String queryParams = "?lat=44.34&lon=10.99&appid=0fca92e2ea86bb3bdf6882f49f492a63";
        String fullUrl = baseUrl + queryParams;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(fullUrl)).GET().build();
        HttpResponse<String> response = null;
        try{
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return response.body();
    }


}
