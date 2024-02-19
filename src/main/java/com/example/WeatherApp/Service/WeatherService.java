package com.example.WeatherApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.IOException;

import com.example.WeatherApp.Repository.WeatherRepo;

public class WeatherService {

    @Autowired
    private WeatherRepo weatherRepo;

    
    public String getWeather(){
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.openweathermap.org/data/2.5/weather")).header("lat", "44.34")
                                                        .header("lon", "10.99").header("appid", "0fca92e2ea86bb3bdf6882f49f492a63")
                                                        .method("GET", HttpRequest.BodyPublishers.noBody()).build();
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
