package com.hlis.api.commands.api.events;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hlis.api.commands.api.entity.Message;
import com.hlis.api.commands.api.repo.MessageRepo;
import com.hlis.common.events.UpdateMessageEvent;

@Component
public class SaveMessageEventHandler {

	@Autowired
	private MessageRepo messageRepo;

	@EventHandler
	public void handle(SaveMessageEvent saveMessageEvent) {
		Message message = new Message();
		BeanUtils.copyProperties(saveMessageEvent, message);
		messageRepo.save(message);

	}

	@EventHandler
	public void handle(UpdateMessageEvent updateMessageEvent) {
		Optional<Message> message = messageRepo.findById(updateMessageEvent.getIdMessage());

		if (message.isPresent()) {
			BeanUtils.copyProperties(updateMessageEvent, message.get());
			messageRepo.save(message.get());

		}

	}

}
