package com.example.WeatherApp.Connector;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Iterator;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.WeatherApp.Entity.WeatherEntity;
import com.example.WeatherApp.Repository.WeatherRepo;

@Component
public class WeatherApi {
      
    @Autowired
    private WeatherRepo weatherRepo;
    
    public WeatherEntity weatherApi(String city){
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
        JSONObject jsonObject1 = new JSONObject(result);
        String main1 = jsonObject1.getJSONArray("weather").getJSONObject(0).getString("main");
        String description1 = jsonObject1.getJSONArray("weather").getJSONObject(0).getString("description");
        Float temp1 = jsonObject1.getJSONObject("main").getFloat("temp");
        Float fealsLike1 = jsonObject1.getJSONObject("main").getFloat("feels_like");
        String city1 = jsonObject1.getString("name");
        LocalDateTime ldt = LocalDateTime.now();
        WeatherEntity weatherEntity = new WeatherEntity(main1, description1, temp1, fealsLike1, city1, ldt);
        weatherRepo.deleteAll();
        return weatherRepo.save(weatherEntity);
    }

    public WeatherEntity weatherApiUpdate(){
        Iterable<WeatherEntity> a = weatherRepo.findAll();
        Iterator<WeatherEntity> b = a.iterator();
        WeatherEntity weatherEntity = b.next();
        String city = weatherEntity.getCity();
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
        JSONObject jsonObject1 = new JSONObject(result);
        String main1 = jsonObject1.getJSONArray("weather").getJSONObject(0).getString("main");
        String description1 = jsonObject1.getJSONArray("weather").getJSONObject(0).getString("description");
        Float temp1 = jsonObject1.getJSONObject("main").getFloat("temp");
        Float fealsLike1 = jsonObject1.getJSONObject("main").getFloat("feels_like");
        String city1 = jsonObject1.getString("name");
        LocalDateTime ldt = LocalDateTime.now();
        weatherEntity = new WeatherEntity(main1, description1, temp1, fealsLike1, city1, ldt);
        weatherRepo.deleteAll();
        return weatherRepo.save(weatherEntity);
    }
}
