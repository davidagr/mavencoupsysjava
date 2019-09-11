package couponsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import couponsystem.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Company findByCompName(String name);

	Company findByCompNameAndPassword(String name, String password);

	Company findByCompId(long id);

	void deleteByCompId(long id);
}
