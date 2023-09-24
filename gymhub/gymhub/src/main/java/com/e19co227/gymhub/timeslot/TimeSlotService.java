package com.e19co227.gymhub.timeslot;

import com.e19co227.gymhub.appuser.AppUser;
import com.e19co227.gymhub.appuser.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotDao timeSlotDao;
    @Autowired
    private AppUserDao appUserDao;

    public ResponseEntity<List<TimeSlot>> getAllTimeSlotsForTrainer(Integer trainerId) {
        return new ResponseEntity<>(timeSlotDao.findByTrainerId(trainerId),HttpStatus.OK);
    }


    public ResponseEntity<String> addTimeSlot(Integer trainerId, TimeSlot timeSlot) {
        AppUser trainer = appUserDao.findByUserId(trainerId);

        if (trainer == null) {
            // Handle the case where the trainer with the given ID is not found.
            return new ResponseEntity<>("Trainer not found for ID: " + trainerId, HttpStatus.NOT_FOUND);
        }

        // Set trainer-related properties in the time slot.
        timeSlot.setTrainerId(trainerId);
        timeSlot.setTrainerUsername(trainer.getUsername());

        try {
            // Save the time slot within a transaction.
            timeSlotDao.save(timeSlot);
            return new ResponseEntity<>("Time slot added", HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any database-related exceptions and return an appropriate response.
            return new ResponseEntity<>("Failed to add time slot: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> updateTimeSlot(Integer timeSlotId, TimeSlot updatedTimeSlot) {
        Optional<TimeSlot> optionalTimeSlot = timeSlotDao.findByTimeSlotId(timeSlotId);

        if (optionalTimeSlot.isPresent()) {
            TimeSlot timeSlot = optionalTimeSlot.get();

            // Check if the updatedTimeSlot has non-null values and update the fields accordingly
            if (updatedTimeSlot.getDate() != null) {
                timeSlot.setDate(updatedTimeSlot.getDate());
            }

            if (updatedTimeSlot.getStartTime() != null) {
                timeSlot.setStartTime(updatedTimeSlot.getStartTime());
            }

            if (updatedTimeSlot.getEndTime() != null) {
                timeSlot.setEndTime(updatedTimeSlot.getEndTime());
            }

            timeSlotDao.save(timeSlot);

            return new ResponseEntity<>("TimeSlot Updated", HttpStatus.OK);
        } else {
            // Handle the case where the time slot with the given ID is not found
            throw new TimeSlotNotFoundException("Time slot not found with ID: " + timeSlotId);
        }
    }

    public ResponseEntity<String> deleteTimeSlot(Integer timeSlotId) {

        if (!timeSlotDao.existsById(timeSlotId)) {
            throw new TimeSlotNotFoundException("Time slot not found with ID: " + timeSlotId);
        }

        timeSlotDao.deleteById(timeSlotId);
        return new ResponseEntity<>("Time slot successfully deleted",HttpStatus.OK);
    }
}
