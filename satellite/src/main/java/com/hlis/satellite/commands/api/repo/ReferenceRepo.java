package com.hlis.satellite.commands.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hlis.satellite.commands.api.entity.Reference;

@Repository
public interface ReferenceRepo extends JpaRepository<Reference, Integer>{

}
