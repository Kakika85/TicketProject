package com.example.ticketproject.controller;

import com.example.ticketproject.model.Ticket;
import com.example.ticketproject.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Test
    void generateTicket() throws Exception {
        Ticket ticket = new Ticket(11245, LocalDateTime.now(), 0);

        when(ticketService.generateTicket()).thenReturn(ticket);

        mockMvc.perform(MockMvcRequestBuilders.post("/tickets/generate"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(11245))
                .andExpect(MockMvcResultMatchers.jsonPath("$.position").value(0));
    }

    @Test
    void getCurrentTicket() throws Exception {
        Ticket ticket = new Ticket(21245, LocalDateTime.now(), 0);

        when(ticketService.getCurrentTicket()).thenReturn(ticket);

        mockMvc.perform(MockMvcRequestBuilders.get("/tickets/current"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(21245))
                .andExpect(MockMvcResultMatchers.jsonPath("$.position").value(0));
    }

    @Test
    void deleteLastTicket() throws Exception {
        doNothing().when(ticketService).deleteLastTicket();

        mockMvc.perform(MockMvcRequestBuilders.delete("/tickets/delete"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(ticketService, times(1)).deleteLastTicket();
    }

    @Test
    void getAllTickets() throws Exception{
        Ticket ticket1 = new Ticket(31245,LocalDateTime.now(),0);
        Ticket ticket2 = new Ticket(31246, LocalDateTime.now(), 1);

        when(ticketService.getAllTickets()).thenReturn(List.of(ticket1,ticket2));

        mockMvc.perform(MockMvcRequestBuilders.get("/tickets/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].number").value(31245))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].number").value(31246));
    }
}
