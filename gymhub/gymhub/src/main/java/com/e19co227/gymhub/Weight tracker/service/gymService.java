package com.example.Gym.service;

import com.example.Gym.dto.gymDto;
import com.example.Gym.entity.gym;
import com.example.Gym.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Gym.repo.gymRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class gymService {


    @Autowired
    private gymRepo gymRepo;
    @Autowired
    private ModelMapper modelMapper;

    public String saveGym(gymDto gymDto){
        if (gymRepo.existsById(gymDto.getGymId())){
            return VarList.RSP_DUPLICATED;
        }else {
            gymRepo.save(modelMapper.map(gymDto, gym.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public List<gymDto> getAllGym(){
        List<gym> gymList = gymRepo.findAll();
        return modelMapper.map(gymList,new TypeToken<ArrayList<gymDto>>(){}.getType());
    }
}
