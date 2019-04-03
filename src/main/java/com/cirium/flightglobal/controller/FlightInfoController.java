package com.cirium.flightglobal.controller;


import com.cirium.flightglobal.service.FlightInfoService;
import com.cirium.flightglobal.service.IFlightInfoService;

/**
 * The controller class.
 * 
 * @author Nags
 *
 */
public class FlightInfoController {
	
	final static String AIRPORTS_FILE_PATH = "src/main/resource/file/airports.csv";
	final static String FLIGHTS_FILE_PATH = "src/main/resource/file/flights.csv";
	final static String OUTPUT_FILE_PATH = "src/main/resource/file/Output.csv";
	
	public static void main(String[] args) throws Exception 
	{
	
		final IFlightInfoService infoService = new FlightInfoService();

		infoService.readAirportDataByLine(AIRPORTS_FILE_PATH);
		
		infoService.readAndProcessFileData(FLIGHTS_FILE_PATH, OUTPUT_FILE_PATH);
	}

}
