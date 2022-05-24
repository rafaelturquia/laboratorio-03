package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Resgate {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id")
    private EmpresaParceira empresa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vantagem_id")
    private Vantagem vantagem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Resgate(Aluno aluno, Vantagem vantagem) {
        this.aluno = aluno;
        this.vantagem = vantagem;
        this.empresa = vantagem.getEmpresa();
    }

    public Resgate(){}

    public EmpresaParceira getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaParceira empresa) {
        this.empresa = empresa;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Vantagem getVantagem() {
        return vantagem;
    }

    public void setVantagem(Vantagem vantagem) {
        this.vantagem = vantagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
