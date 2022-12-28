package com.washing.washingmonitoringsystemrestapi.Dao;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.washing.washingmonitoringsystemrestapi.Entity.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine,Integer> {
    default Machine findByIdOrError(Integer id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    } 
}
