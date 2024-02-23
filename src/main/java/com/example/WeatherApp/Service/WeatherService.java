package com.example.WeatherApp.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.IOException;
import java.util.Date;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

import com.example.WeatherApp.Entity.WeatherEntity;
import com.example.WeatherApp.Repository.WeatherRepo;


@Service
public class WeatherService {

    @Autowired
    private WeatherRepo weatherRepo;


    
    public WeatherEntity getWeather(String city){
        WeatherEntity weatherEntity = weatherRepo.findByCity(city);
        if((weatherRepo.findByCity(city) == null)){
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
        }else{
            Date date1 = new Date();
            Date date2 = java.sql.Timestamp.valueOf(weatherEntity.getUpdateTime());
            Instant instant1 = date1.toInstant();
            Instant instant2 = date2.toInstant();
            Duration duration = Duration.between(instant2, instant1);
            if(duration.toMinutes() > 2){
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
            }else{
                return weatherEntity;
            }
        }
    }

    public boolean updateWeather(WeatherEntity weatherEntity){
        Date date1 = new Date();
        Date date2 = java.sql.Timestamp.valueOf(weatherEntity.getUpdateTime());
        Instant instant1 = date1.toInstant();
        Instant instant2 = date2.toInstant();
 
        Duration duration = Duration.between(instant1, instant2);
        if ( duration.toMinutes() > 2){
            return true;
        }else{
            return false;
        }
    }

    
}
