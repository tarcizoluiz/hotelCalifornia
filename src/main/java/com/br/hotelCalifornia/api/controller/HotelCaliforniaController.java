package com.br.hotelCalifornia.api.controller;

import java.util.List;
import java.util.Optional;
//import java.util.UUID;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.hotelCalifornia.infrastructure.model.Hotel;
import com.br.hotelCalifornia.infrastructure.repository.HotelRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotels")
public class HotelCaliforniaController {
	
    @Autowired
    private HotelRepository hotelRepository;
    
    
    @ResponseBody
    @PostMapping("/criar")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel savedHotel = hotelRepository.save(hotel);
        return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable UUID id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotel.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @ResponseBody
    @GetMapping("/listar")
    public List<Hotel> listar() {
    	List<Hotel> todosHoteis = hotelRepository.findAll();
    	return todosHoteis;
    }

    @PutMapping(value="/alterar/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable(value = "id") UUID id, @RequestBody Hotel hotelDetails) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            hotel.setNome(hotelDetails.getNome());
            hotel.setLocalizacao(hotelDetails.getLocalizacao());
            hotel.setCapacidade(hotelDetails.getCapacidade());
            hotel.setCnpj(hotelDetails.getCnpj());
            Hotel updatedHotel = hotelRepository.save(hotel);
            return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Hotel> deleteHotel(@PathVariable(value = "id") UUID id) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()) {
            hotelRepository.delete(hotelOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}