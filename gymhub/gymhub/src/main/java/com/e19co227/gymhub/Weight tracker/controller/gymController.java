package com.example.Gym.controller;

import com.example.Gym.dto.responseDto;
import com.example.Gym.service.gymService;
import com.example.Gym.dto.gymDto;
import com.example.Gym.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/gym")
@CrossOrigin

public class gymController {

    @Autowired
    private gymService gymService;
    @Autowired
    private responseDto responseDto;

    @PostMapping(value = "/saveGym")
    public ResponseEntity saveGym(@RequestBody gymDto gymDto){
        try {
            String res = gymService.saveGym(gymDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success...!");
                responseDto.setContent(String.valueOf(gymDto));
                return new ResponseEntity(gymDto, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("GYM Registerd...");
                responseDto.setContent(String.valueOf(gymDto));
                return new ResponseEntity(gymDto, HttpStatus.BAD_REQUEST);

            }else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("ERROR...");
                responseDto.setContent(null);
                return new ResponseEntity(gymDto, HttpStatus.BAD_REQUEST);

            }

        } catch (Exception ex){
            responseDto.setCode(VarList.RSP_FAIL);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(gymDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllGym")
    public ResponseEntity getAllGym(){

        try {
            List<gymDto> gymDtoList = gymService.getAllGym();
            responseDto.setCode(VarList.RSP_SUCCESS);
            responseDto.setMessage("Success...!");
            responseDto.setContent(gymDtoList.toString());
            return new ResponseEntity(gymDtoList, HttpStatus.ACCEPTED);

        } catch (Exception ex){
            responseDto.setCode(VarList.RSP_FAIL);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
