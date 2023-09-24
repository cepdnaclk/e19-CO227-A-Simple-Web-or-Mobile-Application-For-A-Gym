package com.e19co227.gymhub.timeslot;

import lombok.AllArgsConstructor;
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

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<TimeSlot>> getAllTimeSlotForTrainer(@PathVariable Integer trainerId){
        return timeSlotService.getAllTimeSlotsForTrainer(trainerId);
    }

    @PostMapping("add/{trainerId}")
    public ResponseEntity<String> addTimeSlot(@PathVariable Integer trainerId,@RequestBody TimeSlot timeSlot){
        return timeSlotService.addTimeSlot(trainerId,timeSlot);
    }

    @PutMapping("/update/{timeSlotId}")
    public ResponseEntity<String> updateTimeSlot(
            @PathVariable Integer timeSlotId,
            @RequestBody TimeSlot updatedTimeSlot) {

        return timeSlotService.updateTimeSlot(timeSlotId, updatedTimeSlot);
    }

    @DeleteMapping("/delete/{timeSlotId}")
    public ResponseEntity<String> deleteTimeSlot(@PathVariable Integer timeSlotId) {
        return timeSlotService.deleteTimeSlot(timeSlotId);

    }
}
