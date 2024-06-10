package com.br.hotelCalifornia.infrastructure.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import java.util.UUID;


@Data
@Entity
@Table(name = "hotel_california")


public class hotelCaliforniaDreams {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "localizacao")
    private String localizacao;
    
    @Column(name = "capacidade")
    private String capacidade;
    
    @Column(name = "cnpj")
    private String cnpj;
    
    
}
