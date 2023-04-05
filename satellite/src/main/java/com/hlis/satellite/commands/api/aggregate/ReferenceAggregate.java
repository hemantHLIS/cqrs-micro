package com.hlis.satellite.commands.api.aggregate;

import java.time.LocalDateTime;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.hlis.common.commands.SaveReferenceCommand;
import com.hlis.common.events.SaveReferenceEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Aggregate
@Data
@NoArgsConstructor
public class ReferenceAggregate {

	@AggregateIdentifier
	private String idReference;
	private String idMessage;
	private Integer value;
	private LocalDateTime dateTime;
	
	@CommandHandler
	public ReferenceAggregate(SaveReferenceCommand saveReferenceCommand) {
		SaveReferenceEvent saveReferenceEvent = new SaveReferenceEvent();
		
		BeanUtils.copyProperties(saveReferenceCommand, saveReferenceEvent);
		
		AggregateLifecycle.apply(saveReferenceEvent);
	}
	
	@EventSourcingHandler
	public void on(SaveReferenceEvent saveReferenceEvent) {
		this.dateTime = saveReferenceEvent.getDateTime();
		this.idMessage = saveReferenceEvent.getIdMessage();
		this.idReference = saveReferenceEvent.getIdReference();
		this.value = saveReferenceEvent.getValue();
	}
	
}
