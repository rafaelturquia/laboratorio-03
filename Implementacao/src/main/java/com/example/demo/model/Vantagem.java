package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Vantagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String descricao;

    private double valor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id")
    private EmpresaParceira empresa;

    @Column(nullable = true, length = 64)
    private String foto;

    private boolean comFoto;

    public Vantagem(EmpresaParceira empresa, Vantagem vantagem) {
        this.empresa = empresa;
        this.valor = vantagem.getValor();
        this.descricao = vantagem.getDescricao();
        this.comFoto = vantagem.isComFoto();
    }

    public Vantagem(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public Vantagem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public EmpresaParceira getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaParceira empresa) {
        this.empresa = empresa;
    }

    public void update(Vantagem vantagem) {
        this.valor = vantagem.getValor();
        this.descricao = vantagem.getDescricao();
        this.foto = foto;
        this.comFoto = vantagem.isComFoto();
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isComFoto() {
        return comFoto;
    }

    public void setComFoto(boolean comFoto) {
        this.comFoto = comFoto;
    }
}
