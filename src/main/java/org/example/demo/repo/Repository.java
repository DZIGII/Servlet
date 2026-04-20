package org.example.demo.repo;

import org.example.demo.database.Day;

import java.util.List;
import java.util.Map;

public interface Repository {

    List<String> loadPonedaljak();
    List<String> loadUtorak();
    List<String> loadSreda();
    List<String> loadCetvrtak();
    List<String> loadPetak();
    void addMeal(Day day, String meal);
    Map<String, Integer> getMealForDay(Day day);
    void printAll();
    void clearAllMeals();

}
