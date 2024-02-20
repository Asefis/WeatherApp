package com.example.WeatherApp.Service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.IOException;

import com.example.WeatherApp.Repository.WeatherRepo;


@Service
public class WeatherService {

    @Autowired
    private WeatherRepo weatherRepo;


    
    public String getWeather(String city){
        String baseUrl = "http://api.openweathermap.org/geo/1.0/direct";
        String queryParams = String.format("?q=%s&appid=0fca92e2ea86bb3bdf6882f49f492a63", city);
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
        String jsonstring = response.body().substring(1, response.body().length() - 1);
        JSONObject jsonObject = new JSONObject(jsonstring);
        double lat = jsonObject.getDouble("lat");
        double lon = jsonObject.getDouble("lon");
        baseUrl = "https://api.openweathermap.org/data/2.5/weather";
        queryParams = String.format("?lat=%f&lon=%f&appid=0fca92e2ea86bb3bdf6882f49f492a63", lat, lon);
        fullUrl = baseUrl + queryParams;
        request = HttpRequest.newBuilder().uri(URI.create(fullUrl)).GET().build();
        response = null;
        try{
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return response.body();
    }

    // public JSONObject getValueToPlace(String city){
    //     String baseUrl = "http://api.openweathermap.org/geo/1.0/direct";
    //     String queryParams = String.format("?q=%s&appid=0fca92e2ea86bb3bdf6882f49f492a63", city);
    //     String fullUrl = baseUrl + queryParams;
    //     HttpRequest request = HttpRequest.newBuilder().uri(URI.create(fullUrl)).GET().build();
    //     HttpResponse<String> response = null;
    //     try{
    //         response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    //     } catch (IOException e){
    //         e.printStackTrace();
    //     } catch (InterruptedException e){
    //         e.printStackTrace();
    //     }
    //     String a = response.body().substring(1, response.body().length() - 1);
    //     JSONObject jsonObject = new JSONObject(a);
    //     return jsonObject;
    // }
}
