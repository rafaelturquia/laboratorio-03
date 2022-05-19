package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "TransferenciaDePontos")
public class TransferenciaDePontos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String motivo;
    private double valor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_od")
    private Professor professor;

    public TransferenciaDePontos(Aluno aluno, Professor professor, TransferenciaDePontos transferenciaDePontos) {
        this.aluno = aluno;
        this.professor = professor;
        this.valor = transferenciaDePontos.getValor();
        this.motivo = transferenciaDePontos.getMotivo();
    }

    public TransferenciaDePontos(String motivo, double valor){
        this.motivo = motivo;
        this.valor = valor;
    }

    public TransferenciaDePontos(){}

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

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
