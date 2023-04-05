package com.hlis.common.commands;

import java.time.LocalDateTime;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveReferenceCommand {
	
	@TargetAggregateIdentifier
	private String idReference;
	private String idMessage;
	private Integer value;
	private LocalDateTime dateTime;

}
