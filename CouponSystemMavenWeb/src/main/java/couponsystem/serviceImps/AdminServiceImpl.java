package couponsystem.serviceImps;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import couponsystem.entities.Company;
import couponsystem.entities.Coupon;
import couponsystem.entities.Customer;
import couponsystem.entities.Income;
import couponsystem.repositories.CompanyRepository;
import couponsystem.repositories.CouponRepository;
import couponsystem.repositories.CustomerRepository;
import couponsystem.repositories.IncomeRepository;
import couponsystem.services.AdminService;

@RestController
@RequestMapping("secure/admin")
public class AdminServiceImpl implements AdminService {

	private static final int SUCCESS = 1;
	private static final int FAILURE = 2;

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private IncomeRepository incomeRepository;

	@Override
	@RequestMapping("create-company")
	public int createCompany(@RequestBody Company company) {
		if (companyRepository.findByCompName(company.getCompName()) == null) {
			companyRepository.save(company);
			return SUCCESS;
		} else {
			return FAILURE;
		}
	}

	@Override
	@Transactional
	@RequestMapping("remove-company/{id}")
	public boolean removeCompany(@PathVariable long id) {
		companyRepository.deleteByCompId(id);
		return true;
	}

	@Override
	@RequestMapping("update-company")
	public int updateCompany(@RequestBody Company company) {
		if (companyRepository.findByCompName(company.getCompName()) == null) {
			companyRepository.save(company);
			return SUCCESS;
		} else {
			return FAILURE;
		}
	}

	@Override
	@RequestMapping("get-company/{id}")
	public Company getCompany(@PathVariable long id) {
		return companyRepository.findByCompId(id);
	}

	@Override
	@RequestMapping("get-all-companies")
	public Collection<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	@RequestMapping("create-customer")
	public int createCustomer(@RequestBody Customer customer) {
		if (customerRepository.findByCustName(customer.getCustName()) == null) {
			customerRepository.save(customer);
			return SUCCESS;
		}else {
			return FAILURE;
		}
	}

	@Override
	@Transactional
	@RequestMapping("remove-customer/{id}")
	public boolean removeCustomer(@PathVariable long id) {
		customerRepository.deleteByCustId(id);
		return true;
	}

	@Override
	@RequestMapping("update-customer")
	public int updateCustomer(@RequestBody Customer customer) {
		if (customerRepository.findByCustName(customer.getCustName()) == null) {
			customerRepository.save(customer);
			return SUCCESS;
		}else {
			return FAILURE;
		}
	}

	@Override
	@RequestMapping("get-customer/{id}")
	public Customer getCustomer(@PathVariable long id) {
		return customerRepository.findByCustId(id);
	}

	@Override
	@RequestMapping("get-all-customers")
	public Collection<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	@RequestMapping("get-all-coupons")
	public Collection<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}

	@Override
	@RequestMapping("get-all-income")
	public Collection<Income> getAllIncome() {
		return incomeRepository.findAll();
	}

	@Override
	@RequestMapping("get-income-sum")
	public double getIncomeSum() {
		Collection<Income> allIncome = incomeRepository.findAll();
		double sum = 0;
		for (Income income : allIncome) {
			sum += income.getAmount();
		}
		return sum;
	}

}
