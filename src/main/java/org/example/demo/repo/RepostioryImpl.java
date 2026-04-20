package org.example.demo.repo;

import org.example.demo.database.DataBase;
import org.example.demo.database.Day;
import org.example.demo.util.FileReader;

import java.util.List;
import java.util.Map;

public class RepostioryImpl implements Repository {

    private String ponedeljakPath;
    private String utorakkPath;
    private String sredaPath;
    private String cetvrtakPath;
    private String petakPath;


    public RepostioryImpl(String ponedeljakPath, String utorakkPath, String sredaPath, String cetvrtakPath, String petakPath) {
        this.ponedeljakPath = ponedeljakPath;
        this.utorakkPath = utorakkPath;
        this.sredaPath = sredaPath;
        this.cetvrtakPath = cetvrtakPath;
        this.petakPath = petakPath;
    }

    @Override
    public List<String> loadPonedaljak() {
        return FileReader.readLines(ponedeljakPath);
    }

    @Override
    public List<String> loadUtorak() {
        return FileReader.readLines(utorakkPath);
    }

    @Override
    public List<String> loadSreda() {
        return FileReader.readLines(sredaPath);
    }

    @Override
    public List<String> loadCetvrtak() {
        return FileReader.readLines(cetvrtakPath);
    }

    @Override
    public List<String> loadPetak() {
        return FileReader.readLines(petakPath);
    }

    @Override
    public void addMeal(Day day, String meal) {
        DataBase.addMeal(day, meal);
    }

    @Override
    public Map<String, Integer> getMealForDay(Day day) {
        return DataBase.getMeelForDay(day);
    }

    @Override
    public void printAll() {
        DataBase.printAll();;
    }

    @Override
    public void clearAllMeals() {
        DataBase.clearAllMeals();
    }

}
