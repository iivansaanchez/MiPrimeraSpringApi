package com.jacaranda.miPrimeraApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.miPrimeraApi.model.Town;

public interface TownRepository extends JpaRepository<Town, String>{

}
