package com.eventura.Mapper;

import com.eventura.DTO.EventDTO;
import com.eventura.Model.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(EventDTO eventDTO);
    EventDTO toDTO(Event event);
}
