package fr.esgi.utils;

import java.util.Date;

public class Booking {

	private Integer bookingId;
	private Date begin;
	private Date end;
	private Date bookingDate;
	private Float price;
	private User user;
	private Workspace workspace;

	public Booking(Integer bookingId, Date begin, Date end, Date bookingDate,
			Float price, User user, Workspace workspace) {
		this.bookingId = bookingId;
		this.begin = begin;
		this.end = end;
		this.bookingDate = bookingDate;
		this.price = price;
		this.user = user;
		this.workspace = workspace;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

}
