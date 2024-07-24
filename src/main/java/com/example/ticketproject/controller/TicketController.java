package com.example.ticketproject.controller;

import com.example.ticketproject.model.Ticket;
import com.example.ticketproject.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/generate")
    public Ticket generateTicket(){
        return ticketService.generateTicket();
    }

    @GetMapping("/current")
    public Ticket getCurrentTicket(){
        return ticketService.getCurrentTicket();
    }

    @GetMapping("/all")
    public List<Ticket> getAllTicket(){
        return ticketService.getAllTickets();
    }

    @DeleteMapping("/delete")
    public void deleteLastTicket(){
        ticketService.deleteLastTicket();
    }
}
