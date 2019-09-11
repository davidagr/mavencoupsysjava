package couponsystem.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import couponsystem.entities.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

	Collection<Income> findByName(String name);

}
