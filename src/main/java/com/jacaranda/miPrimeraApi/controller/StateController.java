package com.jacaranda.miPrimeraApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.miPrimeraApi.model.State;
import com.jacaranda.miPrimeraApi.model.StateDTO;
import com.jacaranda.miPrimeraApi.service.StateService;

@RestController
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	/**
	 * Metodo para obtener todas las provincias
	 * @return Devuelve una lista de todas las provincias que tenemos en la BBDD
	 */
	@GetMapping("/state")
	public List<StateDTO> getAll(){
		return this.stateService.getAll();
	}
	
	/**
	 * Metodo para obtener una provincia especifica por su id
	 * @param id --> Identificador de la provincia
	 * @return 
	 */
	@GetMapping("/state/{id}")
	public ResponseEntity<State> get(@PathVariable String id){
		if(this.stateService.get(id) == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(this.stateService.get(id));
		}
	}

}
