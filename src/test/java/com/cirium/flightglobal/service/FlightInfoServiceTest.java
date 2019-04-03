/**
 * 
 */
package com.cirium.flightglobal.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

/**
 * To verify integrity of service class.
 * 
 * @author Nags
 *
 */
public class FlightInfoServiceTest {

	private FlightInfoService flightInfoService;

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void setUp() {
		flightInfoService = new FlightInfoService();

	}
		
	@Test
	public void testReadAirportDataByLineEmptyFile() throws Exception 
	{
		expectedEx.expect(RuntimeException.class);
	    expectedEx.expectMessage("File path is empty or null");
	    
		flightInfoService.readAirportDataByLine(null);
		
	}

	@Test
	public void testReadAirportDataByLine() throws Exception {
		// Create a temporary file.
		final File tempFile = tempFolder.newFile("tempAirportFile.csv");

		final FileWriter outputfile = new FileWriter(tempFile);
		final CSVWriter writer = new CSVWriter(outputfile);

		final String[] header = { "Airport code", "Airport name", "Time zone" };
		final String[] recordPDX = { "PDX", "Portland, Oregon International Airport", "America/Los_Angeles" };
		final String[] recordDEL = { "DEL", "Indira Gandhi International Airport", "Asia/Kolkata" };
		final String[] recordSFO = { "SFO", "San Francisco International Airport", "America/Los_Angeles" };

		writer.writeNext(header);
		writer.writeNext(recordPDX);
		writer.writeNext(recordDEL);
		writer.writeNext(recordSFO);
		writer.close();

		flightInfoService.readAirportDataByLine(tempFile.getAbsolutePath());

		Map<String, ZoneId> result = flightInfoService.airportInfo;

		Assert.assertEquals(result.get("PDX"), ZoneId.of("America/Los_Angeles"));
	}
	
	@Test
	public void testReadFlightDataByLineForEmptySourceFile() throws Exception 
	{
		expectedEx.expect(RuntimeException.class);
	    expectedEx.expectMessage("File path is empty or null");
	    
		final File outputDataFile = tempFolder.newFile("tempFile.csv");
		
		flightInfoService.readAndProcessFileData(null, outputDataFile.getAbsolutePath());
	}
	
	
	@Test
	public void testReadFlightDataByLineForEmptyDestinationFile() throws Exception 
	{
		expectedEx.expect(RuntimeException.class);
	    expectedEx.expectMessage("File path is empty or null");
	    
	    final File flightsDataFile = tempFolder.newFile("tempFlightFile.csv");
		
		flightInfoService.readAndProcessFileData(flightsDataFile.getAbsolutePath(), null);
	}

	
	@Test
	public void testReadFlightDataByLine() throws Exception {
		// Create a temporary file.
		final File flightsDataFile = tempFolder.newFile("tempFlightFile.csv");
		final File outputDataFile = tempFolder.newFile("tempFile.csv");

		final FileWriter outputfile = new FileWriter(flightsDataFile);
		
		final CSVWriter writer = new CSVWriter(outputfile);
		
		final String[] header = { "Airline", "Flight number", "Departure airport", "Arrival airport",
				"Scheduled local departure", "Actual local departure", "Scheduled local arrival",
				"Actual local arrival" };
		
		final String[] record = {"AI","34","SFO","DEL","2017-01-01T11:30:00", "2017-01-01T11:41:00","2017-01-02T17:15:00",	"2017-01-02T17:33:00"};

		writer.writeNext(header);
		writer.writeNext(record);
		writer.close();

		flightInfoService.readAndProcessFileData(flightsDataFile.getAbsolutePath(), outputDataFile.getAbsolutePath());
		
		// Wait for code to complete
		sleep(2);

		// Read it from temp file
		final FileReader filereader = new FileReader(outputDataFile.getAbsolutePath());
		final CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

		final String[] nextRecord = csvReader.readNext();

		// Verify the content
		Assert.assertEquals("AI 34", nextRecord[0]);
		Assert.assertEquals("11", nextRecord[1]);
		Assert.assertEquals("18", nextRecord[2]);
		Assert.assertEquals("982", nextRecord[3]);
	}
	
	
	private void sleep(int seconds) {

	    try {
	        TimeUnit.SECONDS.sleep(seconds);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
}
