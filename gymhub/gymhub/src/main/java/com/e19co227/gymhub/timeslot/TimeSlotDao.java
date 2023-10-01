package com.e19co227.gymhub.timeslot;

import com.e19co227.gymhub.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface TimeSlotDao extends JpaRepository<TimeSlot,Integer> {

    TimeSlot findByTimeSlotId(Integer timeSlotId);

    List<TimeSlot> getTimeSlotsByTrainee(AppUser trainee);
    List<TimeSlot> getTimeSlotsByTrainer(AppUser trainer);
}
