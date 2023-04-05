package com.hlis.satellite.commands.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hlis.satellite.commands.api.entity.Reference;

public interface ReferenceRepo extends JpaRepository<Reference, String>{

}
