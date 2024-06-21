package com.br.hotelCalifornia.api.controller;

import com.br.hotelCalifornia.domain.exceptions.HotelNaoEncontradoException;
import com.br.hotelCalifornia.domain.services.HotelService;
import com.br.hotelCalifornia.infrastructure.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        try {
            Optional<Hotel> hotel = hotelService.getHotelById(id);
            return hotel.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (HotelNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    public List<Hotel> listar() {
        return hotelService.listarHoteis();
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable UUID id, @RequestBody Hotel hotelDetails) {
        try {
            Hotel updatedHotel = hotelService.updateHotel(id, hotelDetails)
                                             .orElseThrow(() -> new HotelNaoEncontradoException("O Hotel de ID " + id + " não foi encontrado! Não será possível alterá-lo!"));
            return ResponseEntity.ok(updatedHotel);
        } catch (HotelNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable UUID id) {
        try {
            String mensagem = hotelService.deleteHotel(id);
            return ResponseEntity.ok(mensagem);
        } catch (HotelNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Hotel> acharPeloCnpj(@PathVariable String cnpj) {
        try {
            Optional<Hotel> hotel = hotelService.acharPeloCnpj(cnpj);
            return hotel.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (HotelNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}