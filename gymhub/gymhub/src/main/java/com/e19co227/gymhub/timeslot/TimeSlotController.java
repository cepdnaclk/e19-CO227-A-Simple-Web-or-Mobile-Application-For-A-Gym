package com.e19co227.gymhub.timeslot;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/timeslot")
public class TimeSlotController {

    private TimeSlotService timeSlotService;

    // Get all time slots.
    @GetMapping("/allTimeslots")
    public ResponseEntity<List<TimeSlot>> getAllTimeSlots(){
        return timeSlotService.getAllTimeSlots();
    }

    // Get a time slot by its unique identifier (timeSlotId).
    @GetMapping("/{timeSlotId}")
    public ResponseEntity<TimeSlot> getTimeSlotById(@PathVariable("timeSlotId") int timeSlotId) {
        TimeSlot timeSlot = timeSlotService.getTimeSlotById(timeSlotId);
        if (timeSlot != null) {
            return new ResponseEntity<>(timeSlot, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Add a new time slot.
    @PostMapping("add")
    public ResponseEntity<String> addTimeSlot(@RequestBody TimeSlot timeSlot){
        return timeSlotService.addTimeSlot(timeSlot);
    }

    // Update an existing time slot by its unique identifier (timeSlotId).
    @PutMapping("/update/{timeSlotId}")
    public ResponseEntity<TimeSlot> updateTimeSlot(
            @PathVariable("timeSlotId") int timeSlotId,
            @RequestBody TimeSlot timeSlot
            ) {
        TimeSlot updatedTimeSlot = timeSlotService.updateTimeSlot(timeSlotId,timeSlot);
        if (updatedTimeSlot != null) {
            return new ResponseEntity<>(updatedTimeSlot, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a time slot by its unique identifier (timeSlotId).
    @DeleteMapping("/delete/{timeSlotId}")
    public ResponseEntity<Void> deleteTimeSlot(@PathVariable("timeSlotId") int timeSlotId) {
        boolean deleted = timeSlotService.deleteTimeSlot(timeSlotId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
