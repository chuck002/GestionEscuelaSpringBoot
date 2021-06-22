package com.javy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javy.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

}
