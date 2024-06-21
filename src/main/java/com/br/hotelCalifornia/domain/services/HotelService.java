package com.br.hotelCalifornia.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.hotelCalifornia.domain.exceptions.HotelNaoEncontradoException;
import com.br.hotelCalifornia.infrastructure.model.Hotel;
import com.br.hotelCalifornia.infrastructure.repository.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Optional<Hotel> getHotelById(UUID id) {
        return hotelRepository.findById(id);
    }

    public List<Hotel> listarHoteis() {
        return hotelRepository.findAll();
    }

    @Transactional
    public Optional<Hotel> updateHotel(UUID id, Hotel hotelDetails) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            hotel.setNome(hotelDetails.getNome());
            hotel.setLocalizacao(hotelDetails.getLocalizacao());
            hotel.setCapacidade(hotelDetails.getCapacidade());
            hotel.setCnpj(hotelDetails.getCnpj());
            Hotel updatedHotel = hotelRepository.save(hotel);
            return Optional.of(updatedHotel);
        } else {
           throw new HotelNaoEncontradoException("O Hotel de ID " + id + "não foi encontrado! Não será possível alterá-lo!");
        }
    }

    @Transactional
    public String deleteHotel(UUID id) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()) {
            hotelRepository.delete(hotelOptional.get());
            return "Hotel deletado com sucesso";
        } else {
           throw new HotelNaoEncontradoException("Não é possível excluir o hotel de ID " + id + " porque ele não existe!");
        }
    }
    
    public Optional<Hotel> acharPeloCnpj(String cnpj) {
    	Optional<Hotel> hotelOptional = hotelRepository.findByCnpj(cnpj);
        if (hotelOptional.isPresent()) {
        	return hotelOptional;
        	
        } else {
        	throw new HotelNaoEncontradoException("Não foi possível achar o Hotel de cnpj: " + cnpj);
        	
        }
    }
}
