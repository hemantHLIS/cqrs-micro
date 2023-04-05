package com.hlis.api.commands.api.events;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveMessageEvent {

	private Integer idMessage;
	private String message;
	private Integer idReference;
	private LocalDateTime referenceDateTime;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	
}
