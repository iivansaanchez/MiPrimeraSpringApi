package com.jacaranda.miPrimeraApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.miPrimeraApi.model.TownDTO;
import com.jacaranda.miPrimeraApi.service.TownService;

@RestController
public class TownController {
	
	@Autowired
	private TownService townService;
	
	@GetMapping("/town")
	public ResponseEntity<List<TownDTO>> getAll(){
		return ResponseEntity.ok(this.townService.getAll());
	}
	// Obtener una ciudad por su ID
	@GetMapping("/town/{id}")
	public ResponseEntity<TownDTO> getById(@PathVariable String id) {
		return ResponseEntity.ok(townService.getById(id));
	}

	// Crear una nueva ciudad
	@PostMapping("/town")
	public ResponseEntity<TownDTO> create(@RequestBody TownDTO townDTO) {
		return  ResponseEntity.ok(townService.addTown(townDTO));
	}

	// Actualizar información de una ciudad existente
	@PutMapping("/town/{id}")
	public ResponseEntity<TownDTO> update(@PathVariable String id, @RequestBody TownDTO townDTO) {
		return ResponseEntity.ok(townService.updateTown(id, townDTO));
	}

	// Eliminar una ciudad por su ID
	@DeleteMapping("/town/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable String id) {
		return ResponseEntity.ok(townService.delete(id));
	}
}
