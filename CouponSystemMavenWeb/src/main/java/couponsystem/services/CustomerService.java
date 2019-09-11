package couponsystem.services;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import couponsystem.entities.Coupon;
import couponsystem.entities.Customer;

public interface CustomerService {

	Customer getCustomer(HttpSession session);

	Collection<Coupon> getCoupons(HttpSession session);

	int purchaseCoupon(HttpSession session, long id);

	boolean removeCoupon(HttpSession session, long id);

	void removeCoupons(HttpSession session);

}
