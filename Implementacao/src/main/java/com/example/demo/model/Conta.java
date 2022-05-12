package com.example.demo.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Conta")
public class Conta {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private double saldo;

    private ArrayList<Object> historico;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Object> getHistorico() {
        return this.historico;
    }

    public void setHistorico(ArrayList<Object> historico) {
        this.historico = historico;
    }




}
