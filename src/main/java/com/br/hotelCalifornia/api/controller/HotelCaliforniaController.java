package com.br.hotelCalifornia.api.controller;

import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;

import com.br.hotelCalifornia.domain.services.HotelService;
import com.br.hotelCalifornia.infrastructure.model.Hotel;

@RestController
@RequestMapping("/api/hotels")
public class HotelCaliforniaController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/criar")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel savedHotel = hotelService.createHotel(hotel);
        return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable UUID id) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);
        return hotel.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/listar")
    public List<Hotel> listar() {
        return hotelService.listarHoteis();
    }

    @PutMapping(value="/alterar/{id}")
    public ResponseEntity<String> updateHotel(@PathVariable(value = "id") UUID id, @RequestBody Hotel hotelDetails) {
        Optional<Hotel> updatedHotel = hotelService.updateHotel(id, hotelDetails);
        if (updatedHotel.isPresent()) {
        	return ResponseEntity.ok("Hotel Alterado com sucesso");
        } else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o hotel com o ID: "+ id);
        }
                          
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable(value = "id") UUID id) {
        String mensagem = hotelService.deleteHotel(id);
        if (mensagem.equals("Hotel deletado com sucesso")) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
        
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Hotel> acharPeloCnpj(@PathVariable (value = "cnpj") String cnpj) {
        Optional<Hotel> hotel = hotelService.acharPeloCnpj(cnpj);
        return hotel.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }
    

	