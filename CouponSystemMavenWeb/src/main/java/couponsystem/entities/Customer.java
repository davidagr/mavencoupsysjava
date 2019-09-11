package couponsystem.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long custId;
	@Column(unique=true)
	private String custName;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "cust_id")
	private Collection<Coupon> coupons = new ArrayList<Coupon>();

	public Customer() {
	}

	public long getId() {
		return custId;
	}

	public void setId(long id) {
		this.custId = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String castName) {
		this.custName = castName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}

	public void addCoupon(Coupon coupon) {
		coupons.add(coupon);
	}

	public Coupon findCoupon(long id) {
		for (Coupon coupon : coupons) {
			if (coupon.getId() == id) {
				return coupon;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", password=" + password + "]";
	}

	
}