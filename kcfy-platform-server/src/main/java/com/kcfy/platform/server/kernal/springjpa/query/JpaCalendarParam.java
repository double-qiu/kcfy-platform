package com.kcfy.platform.server.kernal.springjpa.query;

import javax.persistence.TemporalType;
import java.util.Calendar;

public class JpaCalendarParam {

	private Calendar calendar;
	
	private TemporalType temporalType;

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public TemporalType getTemporalType() {
		return temporalType;
	}

	public void setTemporalType(TemporalType temporalType) {
		this.temporalType = temporalType;
	}
}
