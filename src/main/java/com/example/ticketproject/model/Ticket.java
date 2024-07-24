package com.example.ticketproject.model;

import java.time.LocalDateTime;

public class Ticket {
    private int number;
    private LocalDateTime dateTime;
    private int position;

    public Ticket(int number, LocalDateTime dateTime, int position){
        this.number = number;
        this.dateTime = dateTime;
        this.position = position;
    }

    public int getNumber(){
        return number;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public int getPosition(){
        return position;
    }

    public void setPosition(int position){
        this.position = position;
    }
}
