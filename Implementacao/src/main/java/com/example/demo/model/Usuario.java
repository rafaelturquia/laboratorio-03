package com.example.demo.model;

import javax.persistence.UniqueConstraint;

public class Usuario {
    private String login;

    private String senha;

    private boolean logado;

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

    public boolean logar(String senha){
        if(senha.equals(getSenha())) {
            logado = true;
            return true;
        } else {
            return false;
        }
    }

    public void deslogar(){
        logado = false;
    }

    public boolean getEstadoLogin(){
        return logado;
    }
}
