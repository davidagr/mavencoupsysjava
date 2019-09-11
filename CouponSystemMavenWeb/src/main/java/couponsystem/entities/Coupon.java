package couponsystem.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import couponsystem.enums.CouponType;

@Entity
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String title;
	private Date start_date;
	private Date end_date;
	private int amount;
	private CouponType type;
	private String message;
	private double price;
	private String image;

	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "comp_id")
	@JsonBackReference
	Company company;

	public Coupon() {
	}

	public Date getStartDate() {
		return start_date;
	}

	public void setStartDate(Date startDate) {
		this.start_date = startDate;
	}

	public Date getEndDate() {
		return end_date;
	}

	public void setEndDate(Date endDate) {
		this.end_date = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	// For when a customer buys one
	public void subtractOne() {
		this.amount -= 1;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public CouponType getType() {
		return type;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Id: " + getId() + ", Title: " + getTitle() + ", Type: " + getType() + ", Start date: " + getStartDate()
				+ ", End date: " + getEndDate() + ", Amount: " + getAmount() + ", Price: " + getPrice() + ", By: "
				+ company.getCompName();
	}

}
