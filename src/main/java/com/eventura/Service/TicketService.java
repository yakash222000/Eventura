package com.eventura.Service;

import com.eventura.DTO.TicketDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    public TicketDTO createTicket(TicketDTO ticketDTO);

    public TicketDTO updateTicket(TicketDTO ticketDTO);

    public TicketDTO getTicketById(long id);

    public void deleteTicket(long id);

    public List<TicketDTO> getAllTicketsForEvent(long eventId);

    public List<TicketDTO> getTicketsByStatusForEvent(long eventId,String status);

    public long getTicketPrice(TicketDTO ticketDTO);

    public TicketDTO upadateTicketStatus(TicketDTO ticketDTO);

    public List<TicketDTO> findTicketsByUserAndEvent(long userId, long eventId);

    public TicketDTO transferTicketOwnership(long ticketId, long userId);



}
