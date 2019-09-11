package couponsystem.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import couponsystem.enums.IncomeType;

@Entity
public class Income {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private Date date;
	private IncomeType description;
	private double amount;

	public Income() {
	}
	
	

	public Income(String name, Date date, IncomeType description, double amount) {
		super();
		this.name = name;
		this.date = date;
		this.description = description;
		this.amount = amount;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public IncomeType getDescription() {
		return description;
	}

	public void setDescription(IncomeType description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Income [id=" + id + ", name=" + name + ", date=" + date + ", description=" + description + ", amount="
				+ amount + "]";
	}

}
