package com.hlis.api.commands.api.command;

import java.time.LocalDateTime;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveMessageCommand {

	
	@TargetAggregateIdentifier
	private String idMessage;
	private String message;
	
	private Integer idReference;
	
	private LocalDateTime referenceDateTime;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
}
