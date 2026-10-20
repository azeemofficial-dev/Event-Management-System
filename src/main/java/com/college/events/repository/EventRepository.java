package com.college.events.repository;

import com.college.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Azeem
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDateGreaterThanEqualOrderByDateAsc(LocalDate date);

    List<Event> findByNameContainingIgnoreCase(String keyword);
}
