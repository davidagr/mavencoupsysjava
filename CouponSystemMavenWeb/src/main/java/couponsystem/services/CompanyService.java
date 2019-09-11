package couponsystem.services;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import couponsystem.entities.Company;
import couponsystem.entities.Coupon;

public interface CompanyService {

	Company getCompany(HttpSession session);

	Collection<Coupon> getCoupons(HttpSession session);

	Coupon getCoupon(HttpSession session, long id);

	int createCoupon(HttpSession session, Coupon coupon);

	int updateCoupon(HttpSession session, Coupon coupon);

	boolean removeCoupon(long id);

}
