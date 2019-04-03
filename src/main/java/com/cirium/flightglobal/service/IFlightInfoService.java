package com.cirium.flightglobal.service;

public interface IFlightInfoService 
{
	/**
	 * Read the airport information from the data excel.
	 * 
	 * @param file The file path.
	 * 
	 * @throws exception.
	 */
	void readAirportDataByLine(String file) throws Exception;
	
	
	/**
	 * Read the flight data, process and make the output file.
	 * 
	 * @param fromFile The flight data file.
	 * @param toFile The output data file.
	 * 
	 * @throws Exception.
	 */
	void readAndProcessFileData(String fromFile, String toFile) throws Exception;

}
