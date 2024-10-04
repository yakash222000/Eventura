package com.eventura.Service;

import com.eventura.DTO.TicketDTO;
import com.eventura.Exception.EventNotFoundException;
import com.eventura.Exception.TicketNotFoundException;
import com.eventura.Exception.UserNotFoundException;
import com.eventura.Mapper.EventMapper;
import com.eventura.Mapper.TicketMapper;
import com.eventura.Mapper.UserMapper;
import com.eventura.Model.Event;
import com.eventura.Model.Ticket;
import com.eventura.Model.User;
import com.eventura.Repository.EventRepository;
import com.eventura.Repository.TicketRepository;
import com.eventura.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserMapper userMapper;
    public TicketServiceImpl(TicketRepository ticketRepository,
                             TicketMapper ticketMapper,
                             UserRepository userRepository,
                             EventRepository eventRepository,
                             EventMapper eventMapper,
                             UserMapper userMapper
                             ){
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.userMapper = userMapper;
    }

    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(ticket);
    }

    @Override
    public TicketDTO updateTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(ticket);
    }

    @Override
    public TicketDTO getTicketById(long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("ticket not found"));
        return ticketMapper.toDTO(ticket);
    }

    @Override
    public void deleteTicket(long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("ticket not found"));
        ticketRepository.delete(ticket);
    }

    @Override
    public List<TicketDTO> getAllTicketsForEvent(long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("event not found"));
        List<Ticket> tickets = event.getTickets();
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketDTOs.add(ticketMapper.toDTO(ticket));
        }
        return ticketDTOs;
    }

    @Override
    public List<TicketDTO> getTicketsByStatusForEvent(long eventId,String status) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("event not found"));
        List<Ticket> tickets = event.getTickets();
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if(ticket.getStatus().equals(status)) {
                ticketDTOs.add(ticketMapper.toDTO(ticket));
            }
        }
        return ticketDTOs;
    }

    @Override
    public long getTicketPrice(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        long price = ticket.getPrice();
        return price;
    }

    @Override
    public TicketDTO upadateTicketStatus(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(ticket);
    }

    @Override
    public List<TicketDTO> findTicketsByUserAndEvent(long userId, long eventId) {
        List<Ticket> tickets = ticketRepository.findByUser_UserIdAndEvent_EventId(userId, eventId);
        return tickets.stream().map(ticketMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TicketDTO transferTicketOwnership(long ticketId, long userId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("ticket not found"));
        User newUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));
        ticket.setUser(newUser);
        ticketRepository.save(ticket);
        return ticketMapper.toDTO(ticket);
    }
}
