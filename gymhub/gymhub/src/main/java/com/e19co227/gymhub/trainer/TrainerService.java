package com.e19co227.gymhub.trainer;

import com.e19co227.gymhub.appointments.Appointment;
import com.e19co227.gymhub.appointments.AppointmentDao;
import com.e19co227.gymhub.appuser.AppUser;
import com.e19co227.gymhub.appuser.AppUserDao;
import com.e19co227.gymhub.timeslot.TimeSlot;
import com.e19co227.gymhub.timeslot.TimeSlotDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private final AppUserDao appUserDao;
    private final AppointmentDao appointmentDao;
    private final TimeSlotDao timeSlotDao;

    @Autowired
    public TrainerService(
            AppUserDao appUserDao,
            AppointmentDao appointmentDao,
            TimeSlotDao timeSlotDao) {
        this.appUserDao = appUserDao;
        this.appointmentDao = appointmentDao;
        this.timeSlotDao = timeSlotDao;
    }
    public Trainer updateTrainerProfile(int trainerId, Trainer trainer) {
        if (appUserDao.existsById(trainerId)) {
            trainer.setUserId(trainerId);
            return appUserDao.save(trainer);
        }
        return null;
    }

    public List<Appointment> getAllAppointmentsForTrainer(int trainerId) {
        AppUser trainer = appUserDao.findByUserId(trainerId).orElse(null);
        if (trainer != null) {
            return appointmentDao.getAppointmentsByTrainer(trainer);
        } else {
            return Collections.emptyList();
        }
    }

    public List<TimeSlot> getAllTimeSlotsForTrainer(int trainerId) {
        AppUser trainer = appUserDao.findByUserId(trainerId).orElse(null);
        if (trainer != null) {
            return timeSlotDao.getTimeSlotsByTrainer(trainer);
        } else {
            return Collections.emptyList();
        }
    }

    public ResponseEntity<String> addTimeSlotForTrainer(int trainerId, TimeSlot timeSlot) {
        AppUser trainer = appUserDao.findByUserId(trainerId).orElse(null);
        if (trainer != null) {
            timeSlot.setTrainer(trainer);
            timeSlotDao.save(timeSlot);
            return new ResponseEntity<>("TimeSlot Added", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Trainer Not Found", HttpStatus.NOT_FOUND);
        }
    }

    public TimeSlot updateTimeSlotForTrainer(int trainerId, int timeSlotId, TimeSlot updatedTimeSlot) {
        AppUser trainer = appUserDao.findByUserId(trainerId).orElse(null);
        if (trainer != null) {
            updatedTimeSlot.setTrainer(trainer);
            updatedTimeSlot.setTimeSlotId(timeSlotId);
            return timeSlotDao.save(updatedTimeSlot);
        }
        return null;
    }

    public boolean deleteTimeSlotForTrainer(int trainerId, int timeSlotId) {

        AppUser trainer = appUserDao.findByUserId(trainerId).orElse(null);
        TimeSlot timeSlot = timeSlotDao.findById(timeSlotId).orElse(null);
        if (timeSlot != null && timeSlot.getTrainer().equals(trainer)) {
            timeSlotDao.deleteById(timeSlotId);
            return true;
        }
        return false;
    }

    public String getUserName(int trainerId) {
        Optional<AppUser> optionalAppUser = appUserDao.findByUserId(trainerId);
        if (optionalAppUser.isPresent()){
            AppUser trainer = optionalAppUser.get();
            return trainer.getUsername();
        }
        return "";
    }


}
