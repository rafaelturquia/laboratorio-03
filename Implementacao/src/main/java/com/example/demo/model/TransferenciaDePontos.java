package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TransferenciaDePontos")
public class TransferenciaDePontos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motivo;
    private double valor;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Aluno aluno;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;


    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
