package couponsystem.serviceImps;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import couponsystem.entities.Coupon;
import couponsystem.entities.Customer;
import couponsystem.entities.Income;
import couponsystem.enums.IncomeType;
import couponsystem.repositories.CouponRepository;
import couponsystem.repositories.CustomerRepository;
import couponsystem.repositories.IncomeRepository;
import couponsystem.services.CustomerService;

@RestController
@RequestMapping("secure/customer")
public class CustomerServiceImpl implements CustomerService {

	private static final int SUCCESS = 1;
	private static final int FAILURE = 2;
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private IncomeRepository incomeRepository;

	@Override
	@RequestMapping("get-customer")
	public Customer getCustomer(HttpSession session) {
		Customer thisCustomer = (Customer) session.getAttribute("user");
		Customer customer = customerRepository.findByCustId(thisCustomer.getId());
		return customer;
	}

	@Override
	@RequestMapping("get-coupons")
	public Collection<Coupon> getCoupons(HttpSession session) {
		Customer thisCustomer = (Customer) session.getAttribute("user");
		Customer customer = customerRepository.findByCustId(thisCustomer.getId());
		return customer.getCoupons();
	}

	@Override
	@RequestMapping("purchase-coupon/{id}")
	public int purchaseCoupon(HttpSession session, @PathVariable long id) {
		Customer thisCustomer = (Customer) session.getAttribute("user");
		Customer customer = customerRepository.findByCustId(thisCustomer.getId());
		Coupon coupon = couponRepository.findById(id);
		if (coupon != null) {
			coupon.subtractOne();
			couponRepository.save(coupon);
			customer.addCoupon(coupon);
			customerRepository.save(customer);
			Income income = new Income(customer.getCustName(), new Date(), IncomeType.CUSTOMER_PURCHASE,
					coupon.getPrice());
			incomeRepository.save(income);
			System.out.println(
					"Coupon " + coupon.getId() + " purchased successfully for customer " + customer.getCustName());
			return SUCCESS;
		} else {
			System.out.println("Coupon does not exist");
			return FAILURE;
		}
	}

	@Override
	@RequestMapping("remove-coupon/{id}")
	public boolean removeCoupon(HttpSession session, @PathVariable long id) {
		Customer thisCustomer = (Customer) session.getAttribute("user");
		Customer customer = customerRepository.findByCustId(thisCustomer.getId());
		Coupon coupon = customer.findCoupon(id);
		if (customer.getCoupons().contains(coupon)) {
			customer.getCoupons().remove(coupon);
			customerRepository.save(customer);
			System.out.println("Coupon " + coupon.getId() + " removed from customer " + customer.getCustName());
			return true;
		} else {
			System.out.println("You do not own this coupon");
			return false;
		}
	}

	@Override
	@RequestMapping("remove-all-coupons")
	public void removeCoupons(HttpSession session) {
		Customer thisCustomer = (Customer) session.getAttribute("user");
		thisCustomer.getCoupons().clear();
		customerRepository.save(thisCustomer);
		System.out.println("Removed all coupons from " + thisCustomer.getCustName());
	}

}
