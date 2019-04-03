package com.cirium.flightglobal.pojo;

/***
 * Flight entity class.
 * 
 * @author Nags
 *
 */
public class Flights {

	private String airline = null;
	private String flightNumber = null;
	private String depatureAirport = null;
	private String arrivalAirport = null;
	private String scheduledDepatureTime = null;
	private String actualDepartureTime = null;
	private String scheduledArrivalTime = null;
	private String actualArrivalTime = null;

	/**
	 * Default constructor for the GenericFlight class.
	 */
	public Flights() {
	}

	/**
	 * @param airline
	 * @param flightNumber
	 * @param depatureAirport
	 * @param arrivalAirport
	 * @param scheduledDepatureTime
	 * @param actualDepartureTime
	 * @param scheduledArrivalTime
	 * @param actualArrivalTime
	 */
	public Flights(String airline, String flightNumber, String depatureAirport, String arrivalAirport,
			String scheduledDepatureTime, String actualDepartureTime, String scheduledArrivalTime,
			String actualArrivalTime) {
		super();
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.depatureAirport = depatureAirport;
		this.arrivalAirport = arrivalAirport;
		this.scheduledDepatureTime = scheduledDepatureTime;
		this.actualDepartureTime = actualDepartureTime;
		this.scheduledArrivalTime = scheduledArrivalTime;
		this.actualArrivalTime = actualArrivalTime;
	}

	/**
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * @param airline the airline to set
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}

	/**
	 * @return the flightNumber
	 */
	public String getFlightNumber() {
		return flightNumber;
	}

	/**
	 * @param flightNumber the flightNumber to set
	 */
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	/**
	 * @return the depatureAirport
	 */
	public String getDepatureAirport() {
		return depatureAirport;
	}

	/**
	 * @param depatureAirport the depatureAirport to set
	 */
	public void setDepatureAirport(String depatureAirport) {
		this.depatureAirport = depatureAirport;
	}

	/**
	 * @return the arrivalAirport
	 */
	public String getArrivalAirport() {
		return arrivalAirport;
	}

	/**
	 * @param arrivalAirport the arrivalAirport to set
	 */
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	/**
	 * @return the scheduledDepatureTime
	 */
	public String getScheduledDepatureTime() {
		return scheduledDepatureTime;
	}

	/**
	 * @param scheduledDepatureTime the scheduledDepatureTime to set
	 */
	public void setScheduledDepatureTime(String scheduledDepatureTime) {
		this.scheduledDepatureTime = scheduledDepatureTime;
	}

	/**
	 * @return the actualDepartureTime
	 */
	public String getActualDepartureTime() {
		return actualDepartureTime;
	}

	/**
	 * @param actualDepartureTime the actualDepartureTime to set
	 */
	public void setActualDepartureTime(String actualDepartureTime) {
		this.actualDepartureTime = actualDepartureTime;
	}

	/**
	 * @return the scheduledArrivalTime
	 */
	public String getScheduledArrivalTime() {
		return scheduledArrivalTime;
	}

	/**
	 * @param scheduledArrivalTime the scheduledArrivalTime to set
	 */
	public void setScheduledArrivalTime(String scheduledArrivalTime) {
		this.scheduledArrivalTime = scheduledArrivalTime;
	}

	/**
	 * @return the actualArrivalTime
	 */
	public String getActualArrivalTime() {
		return actualArrivalTime;
	}

	/**
	 * @param actualArrivalTime the actualArrivalTime to set
	 */
	public void setActualArrivalTime(String actualArrivalTime) {
		this.actualArrivalTime = actualArrivalTime;
	}

	
	

}
