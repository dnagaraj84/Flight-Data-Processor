package com.cirium.flightglobal.pojo;

import java.util.TimeZone;

/**
 * Airport Entity class.
 * 
 * @author Nags
 *
 */
public class Airport {
	
	private String airportCode;
	
	private String airportName;
	
	private TimeZone airportTimeZone;

	/**
	 * @param airportCode
	 * @param airportName
	 * @param airportTimeZone
	 */
	public Airport(String airportCode, String airportName, TimeZone airportTimeZone) {
		super();
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.airportTimeZone = airportTimeZone;
	}

	/**
	 * @return the airportCode
	 */
	public String getAirportCode() {
		return airportCode;
	}

	/**
	 * @param airportCode the airportCode to set
	 */
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	/**
	 * @return the airportName
	 */
	public String getAirportName() {
		return airportName;
	}

	/**
	 * @param airportName the airportName to set
	 */
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	/**
	 * @return the airportTimeZone
	 */
	public TimeZone getAirportTimeZone() {
		return airportTimeZone;
	}

	/**
	 * @param airportTimeZone the airportTimeZone to set
	 */
	public void setAirportTimeZone(TimeZone airportTimeZone) {
		this.airportTimeZone = airportTimeZone;
	}	

}
