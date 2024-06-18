package com.br.hotelCalifornia.infrastructure.model;

//import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;


@Builder
@Entity
@Table(name = "hotel_california")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @Column(name = "id")
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "capacidade")
    private String capacidade;

    @Column(name = "cnpj")
    private String cnpj;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}