package com.eventura.Repository;

import com.eventura.Model.Ticket;
import com.eventura.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser_UserIdAndEvent_EventId(Long userId, Long eventId);
    User findUserByTicketId(Long ticketId);
}
