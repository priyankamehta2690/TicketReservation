package com.example.ticketreservation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by abc on 2/7/2016.
 */
public class DataWrapper implements Serializable {

    private ArrayList<Ticket> Tickets;

    public DataWrapper(ArrayList<Ticket> data) {
        this.Tickets = data;
    }

    public ArrayList<Ticket> getTickets() {
        return this.Tickets;
    }

}
