package com.hlis.satellite.commands.api.query.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hlis.common.query.ReferenceQuery;
import com.hlis.satellite.commands.api.repo.ReferenceRepo;

@Component
public class ReferenceProjection {

	@Autowired
	private ReferenceRepo referenceRepo;
	
	@QueryHandler
	public Long referenceQueryCount(ReferenceQuery referenceQuery) {
		return referenceRepo.count();
	}
	
}
