package com.hlis.api.commands.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hlis.api.commands.api.entity.Message;

public interface MessageRepo extends JpaRepository<Message, String>{

}
