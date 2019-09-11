package couponsystem.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long compId;
	private String compName;
	private String password;
	private String email;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "comp_id")
	@JsonManagedReference
	private Collection<Coupon> coupons = new ArrayList<Coupon>();

	public Company() {
	}

	public long getId() {
		return compId;
	}

	public void setId(long id) {
		this.compId = id;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public Coupon getCoupon(long id) {
		for (Coupon coupon : coupons) {
			if (coupon.getId() == id) {
				return coupon;
			}
		}
		return null;
	}

	public void addCoupon(Coupon c) {
		this.coupons.add(c);
	}

	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Company [compId=" + compId + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ "]";
	}

}