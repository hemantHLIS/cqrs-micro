package com.hlis.api.query.api.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hlis.api.commands.api.repo.MessageRepo;
import com.hlis.api.query.api.MessageQuery;

@Component
public class MessageProjection {

	@Autowired
	private MessageRepo messageRepo;
	
	
	@QueryHandler
	public Long handleMessageCount(MessageQuery messageQuery) {
		return messageRepo.count();
	}
}
