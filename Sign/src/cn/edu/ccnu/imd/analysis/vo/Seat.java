package cn.edu.ccnu.imd.analysis.vo;

/**
 * Seat entity. @author MyEclipse Persistence Tools
 */

public class Seat implements java.io.Serializable {

	// Fields

	private String id;
	private Short deskNum;
	private Short seatNum;
	private String consumerId;
	private String consumerName;
	private String year;
	private String state;

	public Seat() {
	}
	
	public Seat(String id) {
		this.id = id;
	}

	public Seat(String id, Short deskNum, Short seatNum, String consumerId, String consumerName,
			String year, String state) {
		this.id = id;
		this.deskNum = deskNum;
		this.seatNum = seatNum;
		this.consumerId = consumerId;
		this.consumerName = consumerName;
		this.year = year;
		this.state = state;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Short getDeskNum() {
		return this.deskNum;
	}

	public void setDeskNum(Short deskNum) {
		this.deskNum = deskNum;
	}

	public Short getSeatNum() {
		return this.seatNum;
	}

	public void setSeatNum(Short seatNum) {
		this.seatNum = seatNum;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

}