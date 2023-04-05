package com.hlis.api.commands.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hlis.api.commands.api.command.SaveMessageCommand;
import com.hlis.api.query.api.MessageQuery;
import com.hlis.common.model.MessageModel;
import com.hlis.common.utils.ApplicationUtils;

@RestController
@RequestMapping("/messages")
public class ApiCommandController {
	
	private CommandGateway commandGateway;
	private QueryGateway queryGateway;
	
	public ApiCommandController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}
	
	
	@PostMapping("/send")
	public String sendMessages(@RequestBody List<MessageModel> messageModels) {
		
		
		SaveMessageCommand saveMessageCommand = SaveMessageCommand
				.builder()
				.idMessage(UUID.randomUUID().toString())
				.message(ApplicationUtils.generateJSONFromObject(messageModels))
				.createDateTime(LocalDateTime.now())
				.build();
		
		commandGateway.sendAndWait(saveMessageCommand);
		
		return "Message Sent";
	}
	
}
