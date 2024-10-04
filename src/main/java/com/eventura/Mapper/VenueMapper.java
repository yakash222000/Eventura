package com.eventura.Mapper;

import com.eventura.DTO.VenueDTO;
import com.eventura.Model.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    VenueMapper INSTANCE = Mappers.getMapper(VenueMapper.class);

    VenueDTO toDTO(Venue venue);

    Venue toEntity(VenueDTO venueDTO);
}
