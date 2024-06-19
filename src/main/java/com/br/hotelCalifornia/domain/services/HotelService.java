package com.br.hotelCalifornia.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return Optional.empty();
        }
    }

    public String deleteHotel(UUID id) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()) {
            hotelRepository.delete(hotelOptional.get());
            return "Hotel deletado com sucesso";
        } else {
            return "Hotel não encontrado para deletar";
        }
    }
    
    public Optional<Hotel> acharPeloCnpj(String cnpj) {
        return hotelRepository.findById(cnpj);
    }
}
