package com.eventura.Mapper;

import com.eventura.DTO.TicketDTO;
import com.eventura.Model.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Ticket toEntity(TicketDTO ticketDTO);
    TicketDTO toDTO(Ticket ticket);
}
