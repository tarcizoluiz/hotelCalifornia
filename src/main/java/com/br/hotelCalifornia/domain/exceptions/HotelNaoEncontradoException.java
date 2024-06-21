package com.br.hotelCalifornia.domain.exceptions;

@SuppressWarnings("serial")
public class HotelNaoEncontradoException extends RuntimeException {
	
	public HotelNaoEncontradoException(String message) {
		super (message);
	}

}
