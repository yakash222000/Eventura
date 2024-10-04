package com.eventura.Service;

import com.eventura.DTO.EventDTO;
import com.eventura.Exception.EventNotFoundException;
import com.eventura.Mapper.EventMapper;
import com.eventura.Mapper.TicketMapper;
import com.eventura.Mapper.UserMapper;
import com.eventura.Model.Event;
import com.eventura.Repository.EventRepository;
import com.eventura.Repository.TicketRepository;
import com.eventura.Repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserMapper userMapper;
    public EventServiceImpl(TicketRepository ticketRepository,
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
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = eventMapper.toEntity(eventDTO);
        eventRepository.save(event);
        return eventMapper.toDTO(event);
    }

    @Override
    public EventDTO updateEvent(EventDTO eventDTO) {
        Event event = eventMapper.toEntity(eventDTO);
        eventRepository.save(event);
        return eventMapper.toDTO(event);
    }

    @Override
    public void deleteEvent(long id) {
        Event event = eventRepository.findById(id).orElseThrow(()-> new EventNotFoundException("Event not found"));
        eventRepository.delete(event);
    }

    @Override
    public EventDTO getEventById(long id) {
        Event event = eventRepository.findById(id).orElseThrow(()-> new EventNotFoundException("Event not found"));
        return eventMapper.toDTO(event);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            eventDTOs.add(eventMapper.toDTO(event));
        }
        return eventDTOs;
    }

    @Override
    public long checkAvailableSeats(long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException("Event not found"));
        long availableSeats = event.getVenue().getVenueCapacity()-event.getTickets().size();
        return availableSeats;
    }

    @Override
    public EventDTO postponeEvent(long eventId, LocalDateTime startTime, LocalDateTime endTime) {
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException("Event not found"));
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        eventRepository.save(event);
        return eventMapper.toDTO(event);
    }

    @Override
    public List<EventDTO> getEventsDateRange(LocalDateTime start, LocalDateTime end) {
        List<Event> events = eventRepository.findByStartTimeBetween(start, end);
        return events.stream().map(eventMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getPopularEvents() {
        List<Event> events = eventRepository.findAll();

        return events.stream()
                .sorted(Comparator.comparingInt(e -> e.getTickets().size()))
                .limit(10)
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }
}
