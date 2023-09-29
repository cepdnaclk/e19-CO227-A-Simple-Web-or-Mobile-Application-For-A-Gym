package com.e19co227.gymhub.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentDao appointmentDao;


    public ResponseEntity<List<Appointment>> getAllAppointment(){
        return new ResponseEntity<>(appointmentDao.findAll(), HttpStatus.OK);

    }

    public ResponseEntity<String> addAppointment(Appointment appointment) {
        appointmentDao.save(appointment);
        return new ResponseEntity<>("Appointment Added",HttpStatus.CREATED);
    }
}
