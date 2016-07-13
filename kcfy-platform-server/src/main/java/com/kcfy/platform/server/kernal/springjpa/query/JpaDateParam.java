package com.kcfy.platform.server.kernal.springjpa.query;

import javax.persistence.TemporalType;
import java.util.Date;

public class JpaDateParam {

	private Date date;
	
	private TemporalType temporalType;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TemporalType getTemporalType() {
		return temporalType;
	}

	public void setTemporalType(TemporalType temporalType) {
		this.temporalType = temporalType;
	}
}
