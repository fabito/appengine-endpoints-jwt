package com.github.fabito.gaeskeletons.api;

import java.util.Date;

import com.google.api.server.spi.types.DateAndTime;
import com.google.api.server.spi.types.SimpleDate;

public class FooResponse {

	public String status = "status";
	public SimpleDate simpleDate = new SimpleDate(1978, 06, 05);
	public DateAndTime dateAndTime = DateAndTime.parseRfc3339String("1985-04-12T23:20:50.52Z");
	public Date date = new Date();
	
}
