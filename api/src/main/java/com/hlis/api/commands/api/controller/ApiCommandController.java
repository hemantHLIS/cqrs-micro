package com.hlis.api.commands.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
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
		
		Long count = queryGateway.query(new MessageQuery(), ResponseTypes.instanceOf(Long.class)).join();
		
		
		SaveMessageCommand saveMessageCommand = SaveMessageCommand
				.builder()
				.idMessage(count.intValue()+1)
				.message(ApplicationUtils.generateJSONFromObject(messageModels))
				.createDateTime(LocalDateTime.now())
				.build();
		
		commandGateway.sendAndWait(saveMessageCommand);
		
		return "Message Sent";
	}
	
}
