package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Aluno {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String nome;

    private String email;

    private String cpf;

    private String curso;

    private String login;

    private String senha;

    private String instituicao;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void update(Aluno alunoAtualizado) {
        this.cpf = alunoAtualizado.getCpf();
        this.curso = alunoAtualizado.getCurso();
        this.email = alunoAtualizado.getEmail();
        this.instituicao = alunoAtualizado.getInstituicao();
        this.login = alunoAtualizado.getLogin();
        this.senha = alunoAtualizado.getSenha();
        this.nome = alunoAtualizado.getNome();
    }
}