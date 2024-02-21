package com.example.WeatherApp.Service;

import org.json.JSONArray;
import org.json.JSONException;
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
        String result = "";
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
        try {
            JSONArray jsonArray = new JSONArray(response.body());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getString("name").equals(city)){
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
                    result = response.body();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (result.isEmpty()){
            return "Ошибка запроса";
        }else{
            return result;
        }
    }
}
