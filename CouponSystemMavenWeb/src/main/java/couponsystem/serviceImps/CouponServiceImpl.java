package couponsystem.serviceImps;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import couponsystem.entities.Coupon;
import couponsystem.repositories.CouponRepository;
import couponsystem.services.CouponService;

@RestController
@RequestMapping("coupon")
public class CouponServiceImpl implements CouponService {

	@Autowired
	CouponRepository couponRepository;

	@Override
	@RequestMapping("get-all")
	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}
	
}
