package com.e19co227.gymhub.trainer;


import com.e19co227.gymhub.appointments.Appointment;
import com.e19co227.gymhub.timeslot.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PutMapping("/editProfile/{trainerId}")
    public ResponseEntity<Trainer> updateTrainerProfile(@PathVariable int trainerId, @RequestBody Trainer trainer) {
        Trainer updatedTrainer = trainerService.updateTrainerProfile(trainerId, trainer);
        if (updatedTrainer != null) {
            return new ResponseEntity<>(updatedTrainer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get Username for Hi...
    @GetMapping("{trainerId}/username")
    public String getUserName(@PathVariable int trainerId){
        return trainerService.getUserName(trainerId);
    }
    // Get All appointments for a trainer
    @GetMapping("/{trainerId}/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointmentsForTrainer(@PathVariable int trainerId) {
        List<Appointment> appointments = trainerService.getAllAppointmentsForTrainer(trainerId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Get All timeslots for a trainer
    @GetMapping("/{trainerId}/timeslots")
    public ResponseEntity<List<TimeSlot>> getAllTimeSlotForTrainer(@PathVariable int trainerId) {
        List<TimeSlot> timeSlots = trainerService.getAllTimeSlotsForTrainer(trainerId);
        return new ResponseEntity<>(timeSlots, HttpStatus.OK);
    }

    // Add timeslot by Trainer
    @PostMapping("/{trainerId}/addTimeslot")
    public ResponseEntity<String> addTimeSlotForTrainer(
            @PathVariable int trainerId,
            @RequestBody TimeSlot timeSlot) {
        return trainerService.addTimeSlotForTrainer(trainerId, timeSlot);
    }

    // Update timeslot by Trainer
    @PutMapping("/{trainerId}/timeslots/{timeSlotId}")
    public ResponseEntity<TimeSlot> updateTimeSlotForTrainer(
            @PathVariable int trainerId,
            @PathVariable int timeSlotId,
            @RequestBody TimeSlot timeSlot) {
        TimeSlot updatedTimeSlot = trainerService.updateTimeSlotForTrainer(trainerId, timeSlotId, timeSlot);
        if (updatedTimeSlot != null) {
            return new ResponseEntity<>(updatedTimeSlot, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete timeslot by Trainer
    @DeleteMapping("/{trainerId}/timeslots/{timeSlotId}")
    public ResponseEntity<String> deleteTimeSlotForTrainer(
            @PathVariable int trainerId,
            @PathVariable int timeSlotId) {
        boolean deleted = trainerService.deleteTimeSlotForTrainer(trainerId, timeSlotId);
        if (deleted) {
            return new ResponseEntity<>("TimeSlot Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("TimeSlot Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
