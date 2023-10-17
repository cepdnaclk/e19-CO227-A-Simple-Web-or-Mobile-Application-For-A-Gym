package com.e19co227.gymhub.timeslot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class containing business logic for handling time slot-related operations.
 */
@Service
public class TimeSlotService {

    private final TimeSlotDao timeSlotDao;

    @Autowired
    public TimeSlotService(TimeSlotDao timeSlotDao) {
        this.timeSlotDao = timeSlotDao;
    }

    // Retrieves a list of all time slots in the system.
    public ResponseEntity<List<TimeSlot>> getAllTimeSlots(){
        return new ResponseEntity<>(timeSlotDao.findAll(), HttpStatus.OK);

    }

    // Adds a new time slot to the system.
    public ResponseEntity<String> addTimeSlot(TimeSlot timeSlot) {
        timeSlotDao.save(timeSlot);
        return new ResponseEntity<>("TimeSlot Added",HttpStatus.CREATED);
    }

    // Retrieves a time slot by its unique identifier (timeSlotId).
    public TimeSlot getTimeSlotById(int timeSlotId) {
        return timeSlotDao.findByTimeSlotId(timeSlotId);
    }

    // Updates an existing time slot with the provided information
    public TimeSlot updateTimeSlot(int timeSlotId, TimeSlot timeSlot) {
        if (timeSlotDao.existsById(timeSlotId)) {
            timeSlot.setTimeSlotId(timeSlotId);
            return timeSlotDao.save(timeSlot);
        }
        return null;
    }

    // Deletes a time slot by its unique identifier (timeSlotId).
    public boolean deleteTimeSlot(int timeSlotId) {
        if (timeSlotDao.existsById(timeSlotId)) {
            timeSlotDao.deleteById(timeSlotId);
            return true;
        }
        return false;
    }
}
