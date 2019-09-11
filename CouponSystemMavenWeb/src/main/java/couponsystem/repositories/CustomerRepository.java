package couponsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import couponsystem.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByCustName(String name);

	Customer findByCustNameAndPassword(String name, String password);

	Customer findByCustId(long id);

	void deleteByCustId(long id);
}
