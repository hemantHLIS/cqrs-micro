package com.hlis.api.commands.api.saga;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
import com.hlis.common.commands.UpdateMessageCommand;
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
	@EndSaga
	private void handle(SaveMessageEvent saveMessageEvent) {
		log.info("Save message event in saga for id:" + saveMessageEvent.getIdMessage());


		List<MessageModel> messageModels = (List<MessageModel>) ApplicationUtils.generateObjectFromJSON(saveMessageEvent.getMessage(), MessageModel.class, true);
		
		Integer sum =0;
		for (MessageModel messageModel : messageModels) {
			sum += messageModel.getValue();
		}
		
		SaveReferenceCommand saveReferenceCommand = SaveReferenceCommand
				.builder()
				.idMessage(saveMessageEvent.getIdMessage())
				.idReference(UUID.randomUUID().toString())
				.value(sum)
				.dateTime(LocalDateTime.now())
				.build();
		
		commandGateway.sendAndWait(saveReferenceCommand);
	}
	
//	@SagaEventHandler(associationProperty = "idMessage")
//	@EndSaga
//	private void handle(SaveReferenceEvent saveReferenceEvent) {
//		log.info("Saved reference data in db with id::{}",saveReferenceEvent.getIdReference());
//		
//		UpdateMessageCommand updateMessageCommand = UpdateMessageCommand
//				.builder()
//				.idMessage(saveReferenceEvent.getIdMessage())
//				.idReference(saveReferenceEvent.getIdReference())
//				.referenceDateTime(saveReferenceEvent.getDateTime())
//				.updateDateTime(LocalDateTime.now())
//				.build();
//		
//		commandGateway.sendAndWait(updateMessageCommand);
//	
//	}

}
