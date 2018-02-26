package com.example.gaetan.firstapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gaetan on 21-02-18.
 */

public class Prevision {

    private static ArrayList<Prevision> prevision = new ArrayList<>();
    private String name;
    private String country;
    private String tempDay;

    public static void parse(String json) throws JSONException {
        // the purpose of log.i is to give useful informations about the connection.
        Log.i("Prevision", "beginning Parsing");
        prevision.clear();
        JSONArray jsonPrevisions = new JSONArray(json);

        for (int i=0; i<jsonPrevisions.length(); i++) {

            JSONObject jsonPrevision = jsonPrevisions.getJSONObject(i);

            JSONObject cityInformation = jsonPrevision.getJSONObject("city");
            String nameCity = cityInformation.getString("name");
            String country = jsonPrevision.getString("country");

            JSONObject weatherInformation = jsonPrevision.getJSONObject("list");
            JSONObject temperatureInformation = weatherInformation.getJSONObject("temp");
            String temperatureDay = temperatureInformation.getString("day");
            prevision.add(fixPrevision(nameCity, country, temperatureDay));
        }
    }
    Prevision(String name, String country, String tempDay){
        this.name = name;
        this.country = country;
        this.tempDay=tempDay;
    }
    public static Prevision fixPrevision(String name, String country, String tempDay){
        return new Prevision(name, country, tempDay);
    }
    public static String[] getNames() {
        String[] names = new String[prevision.size()];
        for (int i=0; i<prevision.size(); i++) {
            names[i] = prevision.get(i).name;
        }

        return names;
    }

    public static Prevision find(int index) {
        return prevision.get(index);
    }

    public ArrayList<Prevision> getArrayListPrevision(){
        return this.prevision;
    }
    public String getCity(){
        return name;
    }
    public String getCountry(){
        return country;
    }
    public String getTemperatureDay(){
        return tempDay;
    }
}
