package org.example.demo.servlet;


public class AppState {

    private static int resetVersion = 0;

    public static int getResetVersion() {
        return resetVersion;
    }

    public static void incrementResetVersion() {
        resetVersion++;
    }
}
