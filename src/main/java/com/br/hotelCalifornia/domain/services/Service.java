package com.br.hotelCalifornia.domain.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.br.hotelCalifornia.infrastructure.model.Hotel;
import com.br.hotelCalifornia.infrastructure.repository.HotelRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class Service {
	
	private HotelRepository repository;
	
	public List<Hotel> findAll(){
		return repository.findAll();
		
	}
	
	public ResponseEntity<Hotel> encontrar(@PathVariable (value = "id") int id){
		return repository.findById(id)
				.map(mapping -> ResponseEntity.ok().body(mapping))
				.orElse(ResponseEntity.notFound().build());
	}
	
	public Hotel createHotel(@RequestBody Hotel hotelCalifornia) {
		return repository.save(hotelCalifornia);
	}
	
}
