package com.eventura.Service;

import com.eventura.DTO.EventDTO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface EventService {

     EventDTO createEvent(EventDTO eventDTO);

     EventDTO updateEvent(EventDTO eventDTO);

     void deleteEvent(long id);

     EventDTO getEventById(long id);

     List<EventDTO> getAllEvents();

     long checkAvailableSeats(long eventId);

     EventDTO postponeEvent(long eventId, LocalDateTime startTime, LocalDateTime endTime);

     List<EventDTO> getEventsDateRange(LocalDateTime start, LocalDateTime end);

     List<EventDTO> getPopularEvents();

}
