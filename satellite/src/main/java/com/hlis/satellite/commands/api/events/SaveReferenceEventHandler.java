package com.hlis.satellite.commands.api.events;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hlis.common.events.SaveReferenceEvent;
import com.hlis.satellite.commands.api.entity.Reference;
import com.hlis.satellite.commands.api.repo.ReferenceRepo;

@Component
public class SaveReferenceEventHandler {

	@Autowired
	private ReferenceRepo referenceRepo;
	
	@EventHandler
	public void on(SaveReferenceEvent saveReferenceEvent) {
		Reference reference = new Reference();
		BeanUtils.copyProperties(saveReferenceEvent, reference);
		referenceRepo.save(reference);
	}
	
}
