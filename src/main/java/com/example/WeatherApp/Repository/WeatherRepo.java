package com.example.WeatherApp.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.WeatherApp.Entity.WeatherEntity;

public interface WeatherRepo extends CrudRepository<WeatherEntity, Long> {
}
