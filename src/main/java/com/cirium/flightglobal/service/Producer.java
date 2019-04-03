package com.cirium.flightglobal.service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.BlockingQueue;

import com.cirium.flightglobal.pojo.DataSnapshot;
import com.cirium.flightglobal.pojo.Flights;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Producer implements Runnable {
	private final BlockingQueue<DataSnapshot> queue;
	private final DataSnapshot POISON;
	private final String dataFile;

	@Override
	public void run() {

		try {
			process();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (IOException e) {
			 e.printStackTrace();			
		} finally {
			while (true) {
				try {
					queue.put(POISON);
					break;
				} catch (InterruptedException e) {
					 e.printStackTrace();
				}
			}
		}

	}

	private void process() throws InterruptedException, IOException {

		final FileReader filereader = new FileReader(dataFile);

		final CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

		String[] nextRecord;

		while ((nextRecord = csvReader.readNext()) != null) {
			final Flights flight = new Flights();

			flight.setAirline(nextRecord[0]);
			flight.setFlightNumber(nextRecord[1]);
			flight.setDepatureAirport(nextRecord[2]);
			flight.setArrivalAirport(nextRecord[3]);
			flight.setScheduledDepatureTime(nextRecord[4]);
			flight.setActualDepartureTime(nextRecord[5]);
			flight.setScheduledArrivalTime(nextRecord[6]);
			flight.setActualArrivalTime(nextRecord[7]);

			final ZonedDateTime scheduledDepatureZoned = convertTimeFormat(flight.getScheduledDepatureTime(),
					flight.getDepatureAirport());
			final ZonedDateTime actualDeparturedZoned = convertTimeFormat(flight.getActualDepartureTime(),
					flight.getDepatureAirport());

			final ZonedDateTime scheduledArrivalZoned = convertTimeFormat(flight.getScheduledArrivalTime(),
					flight.getArrivalAirport());
			final ZonedDateTime actualArrivalZoned = convertTimeFormat(flight.getActualArrivalTime(),
					flight.getArrivalAirport());

			final DataSnapshot dataSnapshot = new DataSnapshot();

			dataSnapshot.setDepatureDelay(timeDifferenceInMinutes(scheduledDepatureZoned, actualDeparturedZoned));
			dataSnapshot.setArrivalDelay(timeDifferenceInMinutes(scheduledArrivalZoned, actualArrivalZoned));
			dataSnapshot.setTotalDuration(timeDifferenceInMinutes(actualDeparturedZoned, actualArrivalZoned));
			dataSnapshot.setAirportCodeFlightNo(flight.getAirline() + " " + flight.getFlightNumber());
			
			queue.put(dataSnapshot);
		}
	}

	/**
	 * Calculate the difference in time.
	 * 
	 * @param fromtime
	 *            The from time for calculation.
	 * @param toTime
	 *            The to time for calculation.
	 * 
	 * @return The difference in minutes.
	 */
	private long timeDifferenceInMinutes(ZonedDateTime fromtime, ZonedDateTime toTime) {
		long differenceInMinutes = 0;

		if (fromtime != null && toTime != null) {
			differenceInMinutes = ChronoUnit.MINUTES.between(fromtime, toTime);
		}

		return Math.abs(differenceInMinutes);
	}

	/**
	 * Method converts string representation of time to ZoneDataTime object.
	 * 
	 * @param sourceTime
	 *            The string representation of time.
	 * @param airport
	 *            The airport location.
	 * 
	 * @return The ZonedDateTime representation of time.
	 */
	private ZonedDateTime convertTimeFormat(String sourceTime, String airport) {
		ZonedDateTime zonedDateTime = null;

		if (sourceTime.length() > 0 && airport.length() > 0 && FlightInfoService.airportInfo.get(airport) != null) {
			DateTimeFormatter sourceFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");

			zonedDateTime = LocalDateTime.parse(sourceTime, sourceFormat)
					.atZone(FlightInfoService.airportInfo.get(airport));
		}

		return zonedDateTime;
	}

	public Producer(BlockingQueue<DataSnapshot> queue, DataSnapshot POISON, String dataFile) {
		this.queue = queue;
		this.POISON = POISON;
		this.dataFile = dataFile;
	}
}
