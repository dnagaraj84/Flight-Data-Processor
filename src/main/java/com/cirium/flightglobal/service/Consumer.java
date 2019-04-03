package com.cirium.flightglobal.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import com.cirium.flightglobal.pojo.DataSnapshot;
import com.opencsv.CSVWriter;

public class Consumer implements Runnable {
	final BlockingQueue<DataSnapshot> queue;
	private final DataSnapshot POISON;
	private String outputFile;

	@Override
	public void run() {

		try {
			final File file = new File(outputFile);

			// create FileWriter object with file as parameter
			final FileWriter outputfile = new FileWriter(file);

			// create CSVWriter object file writer object as parameter
			final CSVWriter writer = new CSVWriter(outputfile);

			// adding header to csv
			final String[] header = { "Flight identifier", "Departure difference", "Arrival difference", "Duration" };

			writer.writeNext(header);

			while (true) 
			{
				DataSnapshot take = queue.take();
				
				// if this is a poison pill, break, exit
                if (take == POISON) {
                    break;
                }

				process(take, writer);
				
				
			}
			
			// closing writer connection
			writer.close();
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (IOException e) {
			 e.printStackTrace();
		}

	}

	private void process(DataSnapshot result, CSVWriter writer) throws InterruptedException 
	{

		writer.writeNext(new String[] { result.getAirportCodeFlightNo(), "" + result.getDepatureDelay(),
				"" + result.getArrivalDelay(), "" + result.getTotalDuration() });

	}

	public Consumer(BlockingQueue<DataSnapshot> queue, DataSnapshot POISON, String outputFile) {
		this.queue = queue;
		this.POISON = POISON;
		this.outputFile = outputFile;
	}
}
