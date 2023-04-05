package com.hlis.api.commands.api.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Message {

	@Id
	private Integer idMessage;
	private String message;
	
	private Integer idReference;
	
	private LocalDateTime referenceDateTime;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	
}
