package couponsystem.serviceImps;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import couponsystem.entities.Company;
import couponsystem.entities.Coupon;
import couponsystem.entities.Income;
import couponsystem.enums.IncomeType;
import couponsystem.repositories.CompanyRepository;
import couponsystem.repositories.CouponRepository;
import couponsystem.repositories.IncomeRepository;
import couponsystem.services.CompanyService;

@RestController
@RequestMapping("secure/company")
public class CompanyServiceImpl implements CompanyService {

	private static final int SUCCESS = 1;
	private static final int FAILURE = 2;

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private IncomeRepository incomeRepository;

	@Override
	@RequestMapping("get-company")
	public Company getCompany(HttpSession session) {
		Company thisCompany = (Company) session.getAttribute("user");
		Company company = companyRepository.findByCompId(thisCompany.getId());
		return company;
	}

	@Override
	@RequestMapping("get-coupons")
	public Collection<Coupon> getCoupons(HttpSession session) {
		Company thisCompany = (Company) session.getAttribute("user");
		Company company = companyRepository.findByCompId(thisCompany.getId());
		return company.getCoupons();
	}

	@Override
	@RequestMapping("get-coupon/{id}")
	public Coupon getCoupon(HttpSession session, @PathVariable long id) {
		Company thisCompany = (Company) session.getAttribute("user");
		Company company = companyRepository.findByCompId(thisCompany.getId());
		return company.getCoupon(id);
	}

	@Override
	@RequestMapping("create-coupon")
	public int createCoupon(HttpSession session, @RequestBody Coupon coupon) {
		if (coupon.getEndDate().after(new java.sql.Date(System.currentTimeMillis()))
				&& couponRepository.findByTitle(coupon.getTitle()).isEmpty()) {
			Company thisCompany = (Company) session.getAttribute("user");
			coupon.setCompany(thisCompany);
			couponRepository.save(coupon);
			Income income = new Income(thisCompany.getCompName(), new Date(), IncomeType.COMPANY_NEW_COUPON, 100);
			incomeRepository.save(income);
			System.out.println("Coupon created");
			return SUCCESS;
		} else {
			return FAILURE;
		}

	}

	@Override
	@RequestMapping("update-coupon")
	public int updateCoupon(HttpSession session, @RequestBody Coupon coupon) {
		if (coupon.getEndDate().after(new java.sql.Date(System.currentTimeMillis()))) {
			Company thisCompany = (Company) session.getAttribute("user");
			Coupon oldCoup = thisCompany.getCoupon(coupon.getId());
			coupon.setCompany(oldCoup.getCompany());
			coupon.setCompany(thisCompany);
			couponRepository.save(coupon);
			Income income = new Income(thisCompany.getCompName(), new Date(), IncomeType.COMPANY_UPDATE_COUPON, 10);
			incomeRepository.save(income);
			System.out.println("Coupon updated.");
			return SUCCESS;
		} else {
			return FAILURE;
		}
	}

	@Override
	@Transactional
	@RequestMapping("remove-coupon/{id}")
	public boolean removeCoupon(@PathVariable long id) {
		couponRepository.deleteById(id);
		System.out.println("Coupon removed");
		return true;
	}

}
