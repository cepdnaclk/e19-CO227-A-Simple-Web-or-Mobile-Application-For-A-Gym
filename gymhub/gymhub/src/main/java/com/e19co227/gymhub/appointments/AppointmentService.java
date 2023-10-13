package com.e19co227.gymhub.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentDao appointmentDao;

    @Autowired
    public AppointmentService(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }


    public ResponseEntity<List<Appointment>> getAllAppointment(){
        return new ResponseEntity<>(appointmentDao.findAll(), HttpStatus.OK);

    }

    public ResponseEntity<String> addAppointment(Appointment appointment) {
        appointmentDao.save(appointment);
        return new ResponseEntity<>("Appointment Added",HttpStatus.CREATED);
    }

    public Appointment getAppointmentById(int appointmentId) {
        return appointmentDao.findById(appointmentId).orElse(null);
    }

    public Appointment updateAppointment(int appointmentId, Appointment appointment) {
        if (appointmentDao.existsById(appointmentId)) {
            appointment.setAppointmentId(appointmentId);
            return appointmentDao.save(appointment);
        }
        return null;
    }

    public boolean deleteAppointment(int appointmentId) {
        if (appointmentDao.existsById(appointmentId)) {
            appointmentDao.deleteById(appointmentId);
            return true;
        }
        return false;
    }
}
