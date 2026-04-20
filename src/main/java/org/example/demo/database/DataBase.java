package org.example.demo.database;

import java.util.HashMap;
import java.util.Map;

public class DataBase {

    public static Map<Day, Map<String, Integer>> lunches = new HashMap<>();

    static {
        for (Day day : Day.values()) {
            lunches.put(day, new HashMap<>());
        }
    }

    public synchronized static void addMeal(Day day, String meal) {
        Map<String, Integer> dayMeal = lunches.get(day);
        dayMeal.put(meal, dayMeal.getOrDefault(meal, 0) + 1);
    }

    public synchronized static Map<String, Integer> getMeelForDay(Day day) {
        return lunches.get(day);
    }

    public synchronized static void printAll() {
        lunches.forEach((day, meals) -> {
            System.out.println(day + " -> " + meals);
        });
    }

    public synchronized static void clearAllMeals() {
        for (Map<String, Integer> dayMeals : lunches.values()) {
            dayMeals.clear();
        }
    }
}
