package couponsystem.serviceImps;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import couponsystem.entities.Company;
import couponsystem.entities.Customer;
import couponsystem.enums.ClientType;
import couponsystem.repositories.CompanyRepository;
import couponsystem.repositories.CustomerRepository;

@RestController
@RequestMapping("coupon-system")
public class LoginServiceImpl {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping("login/{name}/{password}/{clientType}")
	public boolean login(@PathVariable String name, @PathVariable String password, @PathVariable ClientType clientType,
			HttpSession session) {

		switch (clientType) {

		case ADMIN:
			if (name.equals("admin") && password.equals("1234")) {
				session.setAttribute("user", "admin");
				System.out.println("Admin is logged in");
				return true;
			}
			System.out.println("username or password incorrect");
			return false;

		case COMPANY:
			Company company = companyRepository.findByCompNameAndPassword(name, password);
			if (company != null) {
				session.setAttribute("user", company);
				System.out.println(name + " is logged in");
				return true;
			}
			System.out.println("username or password incorrect");
			return false;

		case CUSTOMER:
			Customer customer = customerRepository.findByCustNameAndPassword(name, password);
			if (customer != null) {
				session.setAttribute("user", customer);
				System.out.println(name + " is logged in");
				return true;
			}
			System.out.println("username or password incorrect");
			return false;

		}
		return false;
	}

}
