package com.example.sande_000.try1;

import java.util.ArrayList;

/**
 * Created by sande_000 on 11/14/2015.
 */
public class CurrentEvent {
    private static CurrentEvent ourInstance = new CurrentEvent();
    private ArrayList<String> events= new ArrayList<String>();
    private int numEvents=0;

    public static CurrentEvent getInstance() {
        return ourInstance;
    }

    private CurrentEvent() {
    }

    public void add(String append){
        //numEvents=1;
        events.add(/*Integer.toString(numEvents) + ": " +*/ append);
    }

    public ArrayList<String> getEvents(){
        return events;
    }
}
