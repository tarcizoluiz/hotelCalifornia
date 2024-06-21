package com.br.hotelCalifornia.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.hotelCalifornia.infrastructure.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {

	Optional<Hotel> findByCnpj(String cnpj);

}
