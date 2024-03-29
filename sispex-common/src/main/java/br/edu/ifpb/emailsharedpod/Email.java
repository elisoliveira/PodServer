package br.edu.ifpb.emailsharedpod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FILIPE
 */
public class Email implements Serializable {

    private Integer id;
    private String remetente;
    private List<Pessoa> destinatarios = new ArrayList<Pessoa>();
    private String assunto;
    private String mensagem;
    private boolean status;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Pessoa> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<Pessoa> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Email pendente: " + "From: <" + remetente + "> \n"
                + "To: <" + destinatarios + "> \n"
                + "Title: " + assunto + ". \n"
                + "Message: " + mensagem + ". \n"
                + "Status: " + status;
    }

}
