/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.edu.br.sixpex.servidor.servicos;

import br.edu.ifpb.emailsharedpod.Email;
import br.edu.ifpb.emailsharedpod.Pessoa;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elisiany
 */
public class SisPexDao implements Serializable {

    public void salvar(Pessoa pessoa) throws RemoteException {
        try {
            String sql = "insert into pessoa (nome, email) values (?, ?)";
            Connection con = ConnectionFactory.getConexao();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getEmail());
            statement.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FachadaService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Pessoa> listaPessoas() throws RemoteException {
        List<Pessoa> listaDePessoas = new ArrayList<>();
        try {
            String sql = "select * from pessoa";
            Connection con = ConnectionFactory.getConexao();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setEmail(resultSet.getString("email"));
                listaDePessoas.add(pessoa);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FachadaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaDePessoas;
    }

    public void salvaEmails(Email email) throws RemoteException {
        try {
            String sql = "insert into email (remetente, destinatarios, assunto, mensagem) values (?, ?, ?, ?)";
            Connection con = ConnectionFactory.getConexao();
            PreparedStatement statement = con.prepareStatement(sql);
            for (Pessoa destinatario : email.getDestinatarios()) {
                statement.setString(1, "ifpbpod@gmail.com");
                statement.setString(2, destinatario.getEmail());
                statement.setString(3, email.getAssunto());
                statement.setString(4, email.getMensagem());
                statement.executeUpdate();
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FachadaService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Email> listaEmailsNaoEnviados() {
        List<Email> listaDeEmailNaoEnviados = new ArrayList<>();
        try {
            String sql = "select * from email where status = false";
            Connection con = ConnectionFactory.getConexao();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setEmail(resultSet.getString("destinatarios"));

                Email email = new Email();
                email.setId(resultSet.getInt("id"));
                email.setAssunto(resultSet.getString("assunto"));
                email.setMensagem(resultSet.getString("mensagem"));
                email.getDestinatarios().add(pessoa);
                
                listaDeEmailNaoEnviados.add(email);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FachadaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaDeEmailNaoEnviados;
    }

    public void atualizaEmail(Email email) {
        try {
            String sql = "update email set status=true where id=?";
            Connection con = ConnectionFactory.getConexao();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, email.getId());
            statement.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SisPexDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
