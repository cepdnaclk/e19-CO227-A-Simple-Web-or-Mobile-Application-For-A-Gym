
package com.e19co227.gymhub.appointments;

// Import necessary dependencies and classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

// Define a service class for managing appointments
@Service
public class AppointmentService {

    // Inject the AppointmentDao for database access
    private final AppointmentDao appointmentDao;

    @Autowired
    public AppointmentService(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    // Get all appointments from the database and return them as a ResponseEntity
    public ResponseEntity<List<Appointment>> getAllAppointment(){
        return new ResponseEntity<>(appointmentDao.findAll(), HttpStatus.OK);

    }

    // Add a new appointment to the database and return a success message
    public ResponseEntity<String> addAppointment(Appointment appointment) {
        appointmentDao.save(appointment);
        return new ResponseEntity<>("Appointment Added",HttpStatus.CREATED);
    }

    // Retrieve an appointment by its ID from the database
    public Appointment getAppointmentById(int appointmentId) {
        return appointmentDao.findById(appointmentId).orElse(null);
    }

    // Update an existing appointment in the database and return the updated appointment
    public Appointment updateAppointment(int appointmentId, Appointment appointment) {
        if (appointmentDao.existsById(appointmentId)) {
            appointment.setAppointmentId(appointmentId);
            return appointmentDao.save(appointment);
        }
        return null;
    }

    // Delete an appointment by its ID from the database and return a success status
    public boolean deleteAppointment(int appointmentId) {
        if (appointmentDao.existsById(appointmentId)) {
            appointmentDao.deleteById(appointmentId);
            return true;
        }
        return false;
    }
}

