package com.example.Gym.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Gym.entity.gym;

public interface gymRepo extends JpaRepository<gym,Integer> {

}
