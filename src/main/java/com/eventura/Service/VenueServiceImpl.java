package com.eventura.Service;

import com.eventura.DTO.VenueDTO;
import com.eventura.Exception.VenueNotFoundException;
import com.eventura.Mapper.EventMapper;
import com.eventura.Mapper.TicketMapper;
import com.eventura.Mapper.UserMapper;
import com.eventura.Mapper.VenueMapper;
import com.eventura.Model.Event;
import com.eventura.Model.Venue;
import com.eventura.Repository.EventRepository;
import com.eventura.Repository.TicketRepository;
import com.eventura.Repository.UserRepository;
import com.eventura.Repository.VenueRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VenueServiceImpl implements VenueService {
    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    private final TicketRepository ticketRepository;

    private final TicketMapper ticketMapper;

    private final VenueRepository venueRepository;

    private final VenueMapper venueMapper;

    public VenueServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           EventRepository eventRepository,
                           EventMapper eventMapper,
                           TicketMapper ticketMapper,
                           TicketRepository ticketRepository,
                            VenueRepository venueRepository,
                            VenueMapper venueMapper
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.venueRepository = venueRepository;
        this.venueMapper = venueMapper;
    }

    @Override
    public VenueDTO addVenue(VenueDTO venueDto) {
        Venue venue = venueMapper.toEntity(venueDto);
        venueRepository.save(venue);
        return venueMapper.toDTO(venue);
    }

    @Override
    public VenueDTO updateVenue(VenueDTO venueDto) {
        Venue venue = venueMapper.toEntity(venueDto);
        venueRepository.save(venue);
        return venueMapper.toDTO(venue);
    }

    @Override
    public VenueDTO getVenue(long venueId) {
        Venue venue = venueRepository.findById(venueId).orElseThrow(()-> new VenueNotFoundException("Venue not found"));
        return venueMapper.toDTO(venue);
    }

    @Override
    public List<VenueDTO> getAllVenues() {
        List<Venue> venues = venueRepository.findAll();
        return venues.stream()
                .map(venueMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVenue(long venueId) {
        Venue venue = venueRepository.findById(venueId).orElseThrow(()-> new VenueNotFoundException("Venue not found"));
        venueRepository.delete(venue);
    }

    @Override
    public boolean isVenueAvailable(long venueId, LocalDateTime startDate, LocalDateTime endDate) {
        Venue venue = venueRepository.findById(venueId).orElseThrow(()-> new VenueNotFoundException("Venue not found"));
        List<Event> events = venue.getEvents();
        for(Event event : events) {
            if (event.getStartTime().isBefore(endDate) && event.getEndTime().isAfter(startDate)) {
                return false;
            }

        }
        return true;
    }

    @Override
    public int getVenueCapacity(long venueId) {
        Venue venue = venueRepository.findById(venueId).orElseThrow(()-> new VenueNotFoundException("Venue not found"));
        return venue.getVenueCapacity();
    }
}
