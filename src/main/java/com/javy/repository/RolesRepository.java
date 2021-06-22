package com.javy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javy.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

}
