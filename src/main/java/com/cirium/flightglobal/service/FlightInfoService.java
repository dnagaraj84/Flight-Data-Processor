package com.cirium.flightglobal.service;

import java.io.FileReader;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang3.StringUtils;

import com.cirium.flightglobal.pojo.DataSnapshot;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * Class which process data based on the input files.
 * 
 * @author Nags
 *
 */
public class FlightInfoService implements IFlightInfoService
{
	public final static Map<String, ZoneId> airportInfo = new HashMap<>();	

	@Override
	public void readAirportDataByLine(String file) throws Exception
	{		
		if(file == null || file.trim().length() == 0)
		{
			throw new RuntimeException("File path is empty or null");
		}
		
		final FileReader filereader = new FileReader(file);

		final CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

		String[] nextRecord;

		while ((nextRecord = csvReader.readNext()) != null) 
		{
			airportInfo.put(nextRecord[0], ZoneId.of(nextRecord[2]));
		}
	}
	
	
	@Override
	public void readAndProcessFileData(String fromFile, String toFile) throws Exception
	{
		if(fromFile == null || fromFile.trim().length() == 0 || toFile == null || toFile.trim().length() == 0)
		{
			throw new RuntimeException("Source/Destination File path is empty or null");
		}
		
		BlockingQueue<DataSnapshot> queue = new LinkedBlockingQueue<>(10);
		
		DataSnapshot poison = new DataSnapshot();
		
		poison.setAirportCodeFlightNo(null);

        new Thread(new Producer(queue,poison, fromFile)).start();
               
        new Thread(new Consumer(queue,poison, toFile)).start();      
	}

}
