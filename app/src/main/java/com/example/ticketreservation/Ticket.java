package com.example.ticketreservation;

/**
 * Created by abc on 2/5/2016.
 */
import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Ticket extends Application implements Serializable {

    public int ticketNumber;
    public String passangerName;
    public String sourceCity;
    public String destinationCity;
    public String departureDate;
    public String departureTime;
    public String returnDate;
    public String returnTime;
    public String typeOfTrip;

    public Ticket(){
        this.departureDate = "";
        this.departureTime = "";
        this.destinationCity = "";
        this.passangerName = "";
        this.returnDate = "";
        this.returnTime = "";
        this.sourceCity = "";
        this.ticketNumber = 0;
        this.typeOfTrip = "";
    }

    public Ticket(String departureDate, String departureTime, String destinationCity, String passangerName, String returnDate, String returnTime, String sourceCity, int ticketNumber, String typeOfTrip) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destinationCity = destinationCity;
        this.passangerName = passangerName;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
        this.sourceCity = sourceCity;
        this.ticketNumber = ticketNumber;
        this.typeOfTrip = typeOfTrip;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getPassangerName() {
        return passangerName;
    }

    public void setPassangerName(String passangerName) {
        this.passangerName = passangerName;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTypeOfTrip() {
        return typeOfTrip;
    }

    public void setTypeOfTrip(String typeOfTrip) {
        this.typeOfTrip = typeOfTrip;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "departureDate='" + departureDate + '\'' +
                ", ticketNumber=" + ticketNumber +
                ", passangerName='" + passangerName + '\'' +
                ", sourceCity='" + sourceCity + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", returnTime='" + returnTime + '\'' +
                ", typeOfTrip='" + typeOfTrip + '\'' +
                '}';
    }
}
