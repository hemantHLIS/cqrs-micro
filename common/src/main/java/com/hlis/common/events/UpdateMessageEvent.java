package com.hlis.common.events;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMessageEvent {
	private String idMessage;
	private String idReference;

	private LocalDateTime referenceDateTime;
	private LocalDateTime updateDateTime;
}
