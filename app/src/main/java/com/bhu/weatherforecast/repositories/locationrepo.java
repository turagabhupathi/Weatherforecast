package com.bhu.weatherforecast.repositories;

import java.util.ArrayList;

public class locationrepo {

    private static locationrepo instance = null;
    public ArrayList<String> city = new ArrayList<String>(); // Member

    public locationrepo() {
        // Exists only to defeat instantiation.
    }
    public static locationrepo getInstance() {
        if(instance == null) {
            instance = new locationrepo();
        }
        return instance;
    }
//    public String getName()
//    {
//        String myName="Chintan Khetiya";
//        return myName;
//    }
//
//    public ArrayList<String> getNameformarray() {
//        name.add("Android");
//        name.add("IPhone");
//        name.add("Windows");
//        return name;
//
//    }


    public ArrayList<String> getCity() {
        return city;
    }

    public void setCity(ArrayList<String> city) {
        this.city = city;
    }
}
