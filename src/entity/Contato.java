/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Fernando Kramer
 */
public class Contato {
    
    private int id;
    private String email;
    private String celular;
    private String tipoCelular;
    private boolean temWhatsApp;
    private String telefone;
    private String tipoTelefone;

    public Contato() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular.replace("(", "").replace(")", "").replace("-", "").replace(" ", "").trim();
    }

    public String getTipoCelular() {
        return tipoCelular;
    }

    public void setTipoCelular(String tipoCelular) {
        this.tipoCelular = tipoCelular.trim();
    }

    public boolean isTemWhatsApp() {
        return temWhatsApp;
    }

    public void setTemWhatsApp(boolean temWhatsApp) {
        this.temWhatsApp = temWhatsApp;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone.replace("(", "").replace(")", "").replace("-", "").replace(" ", "").trim();
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone.trim();
    }
            
}
