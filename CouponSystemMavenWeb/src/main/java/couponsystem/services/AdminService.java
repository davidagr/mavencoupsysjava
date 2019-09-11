package couponsystem.services;

import java.util.Collection;

import couponsystem.entities.Company;
import couponsystem.entities.Coupon;
import couponsystem.entities.Customer;
import couponsystem.entities.Income;

public interface AdminService {

	Collection<Company> getAllCompanies();

	Company getCompany(long id);

	int createCompany(Company company);

	int updateCompany(Company company);

	boolean removeCompany(long id);

	Collection<Customer> getAllCustomers();

	Customer getCustomer(long id);

	int createCustomer(Customer customer);

	int updateCustomer(Customer customer);

	boolean removeCustomer(long id);

	Collection<Income> getAllIncome();

	double getIncomeSum();

	Collection<Coupon> getAllCoupons();

}
