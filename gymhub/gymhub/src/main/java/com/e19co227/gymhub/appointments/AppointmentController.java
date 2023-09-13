package com.e19co227.gymhub.appointments;

import com.e19co227.gymhub.trainee.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("allAppointments")
    public ResponseEntity<List<Appointment>> getAllAppointment(){
        return appointmentService.getAllAppointment();
    }

    @PostMapping("add")
    public ResponseEntity<String> addAppointment(@RequestBody Appointment appointment){
        return appointmentService.addAppointment(appointment);
    }
}
