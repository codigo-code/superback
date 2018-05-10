package com.utn.model.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.utn.model.Bar;

@Component
@Repository
public interface ReservaBar extends JpaRepository<Bar, Integer> {

	
	
}
