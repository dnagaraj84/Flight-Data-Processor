package com.cirium.flightglobal.pojo;

public class DataSnapshot 
{
	private String airportCodeFlightNo;
	
	private long depatureDelay;
	
	private long arrivalDelay;
	
	private long totalDuration;

	
	/**
	 * 
	 */
	public DataSnapshot() {
		super();
	}

	/**
	 * @param airportCodeFlightNo
	 * @param depatureDelay
	 * @param arrivalDelay
	 * @param totalDuration
	 */
	public DataSnapshot(String airportCodeFlightNo, long depatureDelay, long arrivalDelay, long totalDuration) {
		super();
		this.airportCodeFlightNo = airportCodeFlightNo;
		this.depatureDelay = depatureDelay;
		this.arrivalDelay = arrivalDelay;
		this.totalDuration = totalDuration;
	}

	/**
	 * @return the airportCodeFlightNo
	 */
	public String getAirportCodeFlightNo() {
		return airportCodeFlightNo;
	}

	/**
	 * @param airportCodeFlightNo the airportCodeFlightNo to set
	 */
	public void setAirportCodeFlightNo(String airportCodeFlightNo) {
		this.airportCodeFlightNo = airportCodeFlightNo;
	}

	/**
	 * @return the depatureDelay
	 */
	public long getDepatureDelay() {
		return depatureDelay;
	}

	/**
	 * @param depatureDelay the depatureDelay to set
	 */
	public void setDepatureDelay(long depatureDelay) {
		this.depatureDelay = depatureDelay;
	}

	/**
	 * @return the arrivalDelay
	 */
	public long getArrivalDelay() {
		return arrivalDelay;
	}

	/**
	 * @param arrivalDelay the arrivalDelay to set
	 */
	public void setArrivalDelay(long arrivalDelay) {
		this.arrivalDelay = arrivalDelay;
	}

	/**
	 * @return the totalDuration
	 */
	public long getTotalDuration() {
		return totalDuration;
	}

	/**
	 * @param totalDuration the totalDuration to set
	 */
	public void setTotalDuration(long totalDuration) {
		this.totalDuration = totalDuration;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airportCodeFlightNo == null) ? 0 : airportCodeFlightNo.hashCode());
		result = prime * result + (int) (arrivalDelay ^ (arrivalDelay >>> 32));
		result = prime * result + (int) (depatureDelay ^ (depatureDelay >>> 32));
		result = prime * result + (int) (totalDuration ^ (totalDuration >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataSnapshot other = (DataSnapshot) obj;
		if (airportCodeFlightNo == null) {
			if (other.airportCodeFlightNo != null)
				return false;
		} else if (!airportCodeFlightNo.equals(other.airportCodeFlightNo))
			return false;
		if (arrivalDelay != other.arrivalDelay)
			return false;
		if (depatureDelay != other.depatureDelay)
			return false;
		if (totalDuration != other.totalDuration)
			return false;
		return true;
	}
	
	
	

}
