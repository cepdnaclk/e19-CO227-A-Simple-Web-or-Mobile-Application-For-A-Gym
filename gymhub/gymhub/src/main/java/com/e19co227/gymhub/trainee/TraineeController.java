package com.e19co227.gymhub.trainee;

import com.e19co227.gymhub.appointments.Appointment;
import com.e19co227.gymhub.timeslot.TimeSlot;
import com.e19co227.gymhub.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trainee")
public class TraineeController {

    private final TraineeService traineeService;
    private final TrainerService trainerService;
    @Autowired
    public TraineeController(
            TraineeService traineeService,
            TrainerService trainerService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
    }

    // Edit profile
    @PutMapping("/editProfile/{traineeId}")
    public ResponseEntity<Trainee> updateTraineeProfile(@PathVariable int traineeId, @RequestBody Trainee trainee) {
        Trainee updatedTrainee = traineeService.updateTrainerProfile(traineeId, trainee);
        if (updatedTrainee != null) {
            return new ResponseEntity<>(updatedTrainee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get All appointments for a trainee
    @GetMapping("/{traineeId}/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointmentsForTrainee(@PathVariable int traineeId) {
        List<Appointment> appointments = traineeService.getAllAppointmentsForTrainee(traineeId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Get Username for Hi...
    @GetMapping("{traineeId}/username")
    public String getUserName(@PathVariable int traineeId){
        return traineeService.getUserName(traineeId);
    }

    // Get All timeslots of a trainer
    @GetMapping("/{trainerId}/timeslots")
    public ResponseEntity<List<TimeSlot>> getAllTimeSlotForTrainer(@PathVariable int trainerId) {
        List<TimeSlot> timeSlots = trainerService.getAllTimeSlotsForTrainer(trainerId);
        return new ResponseEntity<>(timeSlots, HttpStatus.OK);
    }

    // Add appointment by Trainee
    @PostMapping("/{traineeId}/addAppointment")
    public ResponseEntity<String> addAppointmentByTrainee(
            @PathVariable int traineeId,
            @RequestBody Appointment appointment) {
        return traineeService.addAppointmentByTrainee(traineeId, appointment);
    }

    // Delete appointment by Trainer
    @DeleteMapping("/{traineeId}/appointments/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(
            @PathVariable int traineeId,
            @PathVariable int appointmentId) {
        boolean deleted = traineeService.deleteAppointment(traineeId, appointmentId);
        if (deleted) {
            return new ResponseEntity<>("TimeSlot Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("TimeSlot Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
