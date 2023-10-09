package com.e19co227.gymhub.trainer;

import com.e19co227.gymhub.appointments.Appointment;
import com.e19co227.gymhub.appointments.AppointmentDao;
import com.e19co227.gymhub.appuser.AppUser;
import com.e19co227.gymhub.appuser.AppUserDao;
import com.e19co227.gymhub.timeslot.TimeSlot;
import com.e19co227.gymhub.timeslot.TimeSlotDao;
import com.e19co227.gymhub.trainee.TraineeDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TrainerService {

    private final AppUserDao appUserDao;
    private final TrainerDao trainerDao;
    private final TraineeDao traineeDao;
    private final AppointmentDao appointmentDao;
    private final TimeSlotDao timeSlotDao;

    //@Autowired
    public TrainerService(
            AppUserDao appUserDao,
            TrainerDao trainerDao,
            TraineeDao traineeDao,
            AppointmentDao appointmentDao,
            TimeSlotDao timeSlotDao) {
        this.appUserDao = appUserDao;
        this.trainerDao = trainerDao;
        this.traineeDao = traineeDao;
        this.appointmentDao = appointmentDao;
        this.timeSlotDao = timeSlotDao;
    }

    private AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        AppUser user = appUserDao.findByEmail(email).get();
        return user;
    }
    public Trainer updateTrainerProfile(int trainerId, Trainer trainer) {
        AppUser user = getCurrentUser();

        if (trainerDao.existsById(trainerId)) {
            AppUser existingUser = appUserDao.findById(trainerId).get();
            if(!Objects.equals(existingUser.getUserId(), user.getUserId()))
                throw new IllegalArgumentException("This Appointment Does Not Belong to you");


            trainer.setUserId(trainerId);
            trainerDao.save(trainer);
            return trainer;
        }
        return null;
    }

    public List<Appointment> getAllAppointmentsForTrainer(int trainerId) {
        AppUser user = getCurrentUser();
        AppUser trainer = appUserDao.findByUserId(user.getUserId()).orElse(null);
        if (trainer != null) {
            return appointmentDao.getAppointmentsByTrainer(trainer);
        } else {
            return Collections.emptyList();
        }


    }

    //Directly get from the current user id
    public List<TimeSlot> getAllTimeSlotsForTrainer(int trainerId) {
        AppUser user = getCurrentUser();
        AppUser trainer = appUserDao.findByUserId(user.getUserId()).orElse(null);

        //assert trainer != null;
        if ( trainer == null ) {
            //return Collections.emptyList();
            throw new IllegalArgumentException("This Timeslots not yours");

        }

        return timeSlotDao.getTimeSlotsByTrainer(trainer);

    }

    //Check path variable id and the current user id
    public ResponseEntity<String> addTimeSlotForTrainer(int trainerId, TimeSlot timeSlot) {
        AppUser user = getCurrentUser();
        AppUser trainer = appUserDao.findByUserId(trainerId).orElse(null);

        //assert trainer != null;
        if (trainer == null | !Objects.equals(trainer.getUserId(), user.getUserId()) )
            throw new IllegalArgumentException("This Timeslot Does Not Belong to you");

        timeSlot.setTrainer(trainer);
        timeSlotDao.save(timeSlot);
        return new ResponseEntity<>("TimeSlot Added", HttpStatus.CREATED);


    }

    public TimeSlot updateTimeSlotForTrainer(int trainerId, int timeSlotId, TimeSlot updatedTimeSlot) {
        AppUser user = getCurrentUser();
        AppUser trainer = appUserDao.findByUserId(user.getUserId()).orElse(null);
        if (trainer != null) {
            updatedTimeSlot.setTrainer(trainer);
            updatedTimeSlot.setTimeSlotId(timeSlotId);
            return timeSlotDao.save(updatedTimeSlot);
        }
        return null;
    }

    public boolean deleteTimeSlotForTrainer(int trainerId, int timeSlotId) {

        AppUser user = getCurrentUser();
        AppUser trainer = appUserDao.findByUserId(user.getUserId()).orElse(null);
        TimeSlot timeSlot = timeSlotDao.findById(timeSlotId).orElse(null);
        if (timeSlot != null && timeSlot.getTrainer().equals(trainer)) {
            timeSlotDao.deleteById(timeSlotId);
            return true;
        }
        return false;
    }

    public String getUserName(int trainerId) {
        AppUser user = getCurrentUser();
        Optional<AppUser> optionalAppUser = appUserDao.findByUserId(user.getUserId());
        if (optionalAppUser.isPresent()){
            AppUser trainer = optionalAppUser.get();
            return "Hi " + trainer.getUsername();
        }
        return "";
    }


}
