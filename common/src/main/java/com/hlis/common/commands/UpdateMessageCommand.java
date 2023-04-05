package com.hlis.common.commands;

import java.time.LocalDateTime;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMessageCommand {
	@TargetAggregateIdentifier
	private Integer idMessage;
	private Integer idReference;

	private LocalDateTime referenceDateTime;
	private LocalDateTime updateDateTime;

}
