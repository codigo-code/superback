package com.utn.model.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.model.User;

@Repository
public interface IUserMethod extends JpaRepository<User,Integer> {

}
