package com.hlis.satellite.commands.api.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class Reference {

	@Id
	private Integer idReference;
	private Integer idMessage;
	private Integer value;
	private LocalDateTime dateTime;
}
