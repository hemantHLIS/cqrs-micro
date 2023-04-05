package com.hlis.api.commands.api.saga;

import java.time.LocalDateTime;
import java.util.List;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.hlis.api.commands.api.events.SaveMessageEvent;
import com.hlis.common.commands.SaveReferenceCommand;
import com.hlis.common.events.SaveReferenceEvent;
import com.hlis.common.model.MessageModel;
import com.hlis.common.query.ReferenceQuery;
import com.hlis.common.utils.ApplicationUtils;

import lombok.extern.slf4j.Slf4j;

@Saga
@Slf4j
public class MessageProcessingSaga {
	@Autowired
	private CommandGateway commandGateway;
	@Autowired
	private QueryGateway queryGateway;

	@SuppressWarnings("unchecked")
	@SagaEventHandler(associationProperty = "idMessage")
	@StartSaga
	private void handle(SaveMessageEvent saveMessageEvent) {
		log.info("Save message event in saga for id:" + saveMessageEvent.getIdMessage());

		Long referenceCount = queryGateway.query(new ReferenceQuery(), ResponseTypes.instanceOf(Long.class)).join();

		List<MessageModel> messageModels = (List<MessageModel>) ApplicationUtils.generateObjectFromJSON(saveMessageEvent.getMessage(), MessageModel.class, true);
		
		Integer sum =0;
		for (MessageModel messageModel : messageModels) {
			sum += messageModel.getValue();
		}
		
		SaveReferenceCommand saveReferenceCommand = SaveReferenceCommand
				.builder()
				.idMessage(saveMessageEvent.getIdMessage())
				.idReference(referenceCount.intValue()+1)
				.value(sum)
				.dateTime(LocalDateTime.now())
				.build();
		
		commandGateway.sendAndWait(saveReferenceCommand);
	}
	
	@SagaEventHandler(associationProperty = "idReference")
	@EndSaga
	private void handle(SaveReferenceEvent saveReferenceEvent) {
		log.info("Saved reference data in db with id::{}",saveReferenceEvent.getIdReference());
		
		
	}

}
