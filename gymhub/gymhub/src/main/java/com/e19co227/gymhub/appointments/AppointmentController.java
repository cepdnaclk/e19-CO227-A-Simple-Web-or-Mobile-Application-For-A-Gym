package com.e19co227.gymhub.appointments;

// Import necessary dependencies and classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Define a REST controller for handling appointment-related operations
@RestController
@RequestMapping("api/v1/appointment")
public class AppointmentController {

    // Inject the AppointmentService to handle business logic
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Define an endpoint to retrieve a list of all appointments
    @GetMapping("allAppointments")
    public ResponseEntity<List<Appointment>> getAllAppointment(){

        return appointmentService.getAllAppointment();
    }

    // Define an endpoint to retrieve an appointment by its ID
    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("appointmentId") int id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Define an endpoint to add a new appointment
    @PostMapping("add")
    public ResponseEntity<String> addAppointment(@RequestBody Appointment appointment){
        return appointmentService.addAppointment(appointment);
    }

    // Define an endpoint to update an existing appointment
    @PutMapping("/update/{appointmentId}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable("appointmentId") int appointmentId,
            @RequestBody Appointment appointment
    ) {
        Appointment updatedAppointment = appointmentService.updateAppointment(appointmentId, appointment);
        if (updatedAppointment != null) {
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Define an endpoint to delete an appointment by its ID
    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("appointmentId") int id) {
        boolean deleted = appointmentService.deleteAppointment(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
