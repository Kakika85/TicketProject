package com.example.ticketproject.service;

import com.example.ticketproject.model.Ticket;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class TicketService {
    private List<Ticket> tickets = new LinkedList<>();
    private int nextNumber = 1245;

    public Ticket generateTicket(){
        Ticket ticket= new Ticket(nextNumber++, LocalDateTime.now(), tickets.size());
        tickets.add(ticket);
        return ticket;
    }
    public Ticket getCurrentTicket(){
        return tickets.isEmpty() ? null : tickets.get(0);
    }

    public void deleteLastTicket(){
        if (!tickets.isEmpty()){
            tickets.remove(0);
            for (int i = 0; i < tickets.size(); i++) {
                tickets.get(i).setPosition(i);
            }
        }
    }

    public List<Ticket> getAllTickets(){
        return tickets;
    }
}
