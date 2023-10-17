package com.e19co227.gymhub.timeslot;

import com.e19co227.gymhub.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface TimeSlotDao extends JpaRepository<TimeSlot,Integer> {

    // Find a time slot by its unique identifier.
    TimeSlot findByTimeSlotId(Integer timeSlotId);

    // Find all time slots associated with a specific trainee (AppUser entity).
    List<TimeSlot> getTimeSlotsByTrainee(AppUser trainee);

    // Find all time slots associated with a specific trainer (AppUser entity).
    List<TimeSlot> getTimeSlotsByTrainer(AppUser trainer);
}
