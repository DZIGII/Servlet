package org.example.demo.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<String> readLines(String path) {

        List<String> res = new ArrayList<>();

        try {
            InputStream is = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream(path);

            if (is == null) {
                throw new RuntimeException("Fajl nije pronađen: " + path);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;

            while ((line = br.readLine()) != null) {
                res.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}