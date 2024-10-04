package com.eventura.Service;

import com.eventura.DTO.VenueDTO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface VenueService {
    public VenueDTO addVenue(VenueDTO venue);

    public VenueDTO updateVenue(VenueDTO venue);

    public VenueDTO getVenue(long venueId);

    public List<VenueDTO> getAllVenues();

    public void deleteVenue(long venueId);

    public boolean isVenueAvailable(long venueId, LocalDateTime startDate, LocalDateTime endDate);

    public int getVenueCapacity(long venueId);

}
