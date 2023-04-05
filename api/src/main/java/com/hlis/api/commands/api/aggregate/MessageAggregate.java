package com.hlis.api.commands.api.aggregate;

import java.time.LocalDateTime;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.hlis.api.commands.api.command.SaveMessageCommand;
import com.hlis.api.commands.api.events.SaveMessageEvent;
import com.hlis.common.commands.UpdateMessageCommand;
import com.hlis.common.events.UpdateMessageEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Aggregate
@Data
@NoArgsConstructor
public class MessageAggregate {

	@AggregateIdentifier
	private Integer idMessage;
	private String message;
	
	private Integer idReference;
	
	private LocalDateTime referenceDateTime;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	
	
	@CommandHandler
	public MessageAggregate(SaveMessageCommand saveMessageCommand ) {
		SaveMessageEvent saveMessageEvent = new SaveMessageEvent();
		BeanUtils.copyProperties(saveMessageCommand, saveMessageEvent);
		
		AggregateLifecycle.apply(saveMessageEvent);
	}
	
	@EventSourcingHandler
	public void on(SaveMessageEvent saveMessageEvent) {
		this.message = saveMessageEvent.getMessage();
		this.idMessage = saveMessageEvent.getIdMessage();
		this.createDateTime = saveMessageEvent.getCreateDateTime();
		this.idReference = saveMessageEvent.getIdReference();
		this.referenceDateTime = saveMessageEvent.getReferenceDateTime();
		this.updateDateTime = saveMessageEvent.getUpdateDateTime();
	}
	
	@CommandHandler
	public void handle(UpdateMessageCommand updateMessageCommand) {
		UpdateMessageEvent updateMessageEvent = new UpdateMessageEvent();
		BeanUtils.copyProperties(updateMessageCommand, updateMessageEvent);
		AggregateLifecycle.apply(updateMessageEvent);
	}
	
	@EventSourcingHandler
	public void on(UpdateMessageEvent updateMessageEvent) {
		this.idMessage = updateMessageEvent.getIdMessage();
		this.idReference = updateMessageEvent.getIdReference();
		this.referenceDateTime = updateMessageEvent.getReferenceDateTime();
		this.updateDateTime = updateMessageEvent.getUpdateDateTime();
	}
}
