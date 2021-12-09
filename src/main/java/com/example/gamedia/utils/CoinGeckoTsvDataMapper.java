package com.example.gamedia.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class CoinGeckoTsvDataMapper {

    public static Map<String,String> Data = new HashMap<>();

    public static void tsvr(File test2) {

        try (BufferedReader TSVReader = new BufferedReader(new FileReader(test2))) {

            String line;

            while ((line = TSVReader.readLine()) != null) {
                Data.put(line.split("\t")[1].toLowerCase(),line.split("\t")[0]);
            }

        } catch (Exception e) {
            System.out.println("Convert from .tsv to ArrayList: error!");
        }

        System.out.println("Convert from .tsv to ArrayList: correct!");
    }

}
