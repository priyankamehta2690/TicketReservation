package com.example.ticketreservation;

import java.util.ArrayList;

/**
 * Created by abc on 2/9/2016.
 */
public class Globals {
    private static Globals instance;
    private static ArrayList<Ticket> GlobalTicketList;

    private Globals(){

    }

    public static ArrayList<Ticket> getGlobalTicketList() {
        return GlobalTicketList;
    }

    public static void setGlobalTicketList(ArrayList<Ticket> globalTicketList) {
        Globals.GlobalTicketList = globalTicketList;
    }

    public static synchronized Globals getInstance(){
        if(instance==null)
            instance = new Globals();

            return instance;
    }
}
