package com.e19co227.gymhub.timeslot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSlotService {

    private final TimeSlotDao timeSlotDao;

    @Autowired
    public TimeSlotService(TimeSlotDao timeSlotDao) {
        this.timeSlotDao = timeSlotDao;
    }


    public ResponseEntity<List<TimeSlot>> getAllTimeSlots(){
        return new ResponseEntity<>(timeSlotDao.findAll(), HttpStatus.OK);

    }

    public ResponseEntity<String> addTimeSlot(TimeSlot timeSlot) {
        timeSlotDao.save(timeSlot);
        return new ResponseEntity<>("TimeSlot Added",HttpStatus.CREATED);
    }

    public TimeSlot getTimeSlotById(int timeSlotId) {
        return timeSlotDao.findByTimeSlotId(timeSlotId);
    }

    public TimeSlot updateTimeSlot(int timeSlotId, TimeSlot timeSlot) {
        if (timeSlotDao.existsById(timeSlotId)) {
            timeSlot.setTimeSlotId(timeSlotId);
            return timeSlotDao.save(timeSlot);
        }
        return null;
    }

    public boolean deleteTimeSlot(int timeSlotId) {
        if (timeSlotDao.existsById(timeSlotId)) {
            timeSlotDao.deleteById(timeSlotId);
            return true;
        }
        return false;
    }
}
