package com.example.demo.model;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "login", "isActive" }) })
public class EmpresaParceira extends Usuario{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String nome;

    private String cnpj;

    private String senha;

    @Column(unique=true)
    private String login;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }
}