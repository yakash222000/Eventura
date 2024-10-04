package com.eventura.Repository;

import com.eventura.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
