package com.br.hotelCalifornia.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.hotelCalifornia.domain.exceptions.HotelNaoEncontradoException;

public class GlobalException {
	
	@ExceptionHandler(HotelNaoEncontradoException.class)
    public ResponseEntity<String> handleHotelNaoEncontradoException(HotelNaoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Outros manipuladores de exceções podem ser adicionados aqui
}


