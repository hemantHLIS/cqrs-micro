package com.hlis.common.events;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveReferenceEvent {

	
	private String idReference;
	private String idMessage;
	private Integer value;
	private LocalDateTime dateTime;
}
