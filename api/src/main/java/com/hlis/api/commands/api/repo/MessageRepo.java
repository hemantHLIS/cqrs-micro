package com.hlis.api.commands.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hlis.api.commands.api.entity.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, Integer>{

}
