package com.e19co227.gymhub.timeslot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TimeSlotDao extends JpaRepository<TimeSlot,Integer> {

    List<TimeSlot> findByTrainerId(Integer trainerId);
    Optional<TimeSlot> findByTimeSlotId(Integer timeSlotId);
}
