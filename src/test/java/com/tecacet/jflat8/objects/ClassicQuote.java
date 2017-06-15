package com.tecacet.jflat8.objects;

import java.util.Date;

/**
 * Bean with getters setters and default constructor
 * 
 * @author dimitri
 *
 */
public class ClassicQuote {

	private Date date;
	private double open;
	private double close;
	private long volume;

	public ClassicQuote() {

	}

	public ClassicQuote(Date date, double open, double close, long volume) {
		super();
		this.date = date;
		this.open = open;
		this.close = close;
		this.volume = volume;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "Quote1 [date=" + date + ", open=" + open + ", close=" + close + ", volume=" + volume + "]";
	}

}
