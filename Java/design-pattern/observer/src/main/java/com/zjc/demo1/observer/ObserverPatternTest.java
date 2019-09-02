package com.zjc.demo1.observer;

import com.zjc.demo1.observer.observer.CurrentConditionsDisplay;
import com.zjc.demo1.observer.observer.ForecastDisplay;
import com.zjc.demo1.observer.subject.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class ObserverPatternTest {
    public static void main(String[] args) {

        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        List<Float> forecastTemperatures = new ArrayList<Float>();
        forecastTemperatures.add(22f);
        forecastTemperatures.add(-1f);
        forecastTemperatures.add(9f);
        forecastTemperatures.add(23f);
        forecastTemperatures.add(27f);
        forecastTemperatures.add(30f);
        forecastTemperatures.add(10f);
        weatherData.setMeasurements(22f, 0.8f, 1.2f, forecastTemperatures);

        System.out.println("============================================");

        List<Float> forecastTemperatures2 = new ArrayList<Float>();
        forecastTemperatures2.add(2f);
        forecastTemperatures2.add(9f);
        forecastTemperatures2.add(9f);
        forecastTemperatures2.add(13f);
        forecastTemperatures2.add(17f);
        forecastTemperatures2.add(20f);
        forecastTemperatures2.add(20f);
        weatherData.setMeasurements(2f, 0.7f, 1.1f, forecastTemperatures2);

        System.out.println("============================================");

        List<Float> forecastTemperatures3 = new ArrayList<Float>();
        forecastTemperatures3.add(21f);
        forecastTemperatures3.add(11f);
        forecastTemperatures3.add(12f);
        forecastTemperatures3.add(33f);
        forecastTemperatures3.add(37f);
        forecastTemperatures3.add(30f);
        forecastTemperatures3.add(20f);
        weatherData.setMeasurements(21f, 0.6f, 0.9f, forecastTemperatures3);
    }
}
