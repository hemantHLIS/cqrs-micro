package com.hlis.satellite.commands.api.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.hlis.common.commands.UpdateMessageCommand;
import com.hlis.common.events.SaveReferenceEvent;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Saga
@NoArgsConstructor
@Slf4j
public class ReferenceProcessingSaga {

	
	@Autowired
	private CommandGateway commandGateway;
	
	
	@SagaEventHandler(associationProperty = "idReference")
	@StartSaga
	@EndSaga
	public void handle(SaveReferenceEvent saveReferenceEvent) {
		log.info("saved reference event with id: {}",saveReferenceEvent.getIdReference());
		
		UpdateMessageCommand updateMessageCommand = UpdateMessageCommand
				.builder()
				.idMessage(saveReferenceEvent.getIdMessage())
				.idReference(saveReferenceEvent.getIdReference())
				.referenceDateTime(saveReferenceEvent.getDateTime())
				.updateDateTime(saveReferenceEvent.getDateTime())
				.build();
		
		commandGateway.sendAndWait(updateMessageCommand);
	}
	
}
