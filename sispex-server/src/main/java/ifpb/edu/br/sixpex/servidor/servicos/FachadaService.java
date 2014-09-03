/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.edu.br.sixpex.servidor.servicos;

import br.edu.ifpb.emailsharedpod.Email;
import br.edu.ifpb.emailsharedpod.Fachada;
import br.edu.ifpb.emailsharedpod.Pessoa;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Elisiany
 */
public class FachadaService implements Fachada {

    @Override
    public String enviaEmail(Email email) throws RemoteException {
        salvaEmail(email);
        String resultado = "Email(s) enviado(s) com sucesso!";
        try {
            SimpleEmail simpleEmail = new SimpleEmail();
            simpleEmail.setSmtpPort(587);
            simpleEmail.setTLS(true);
            simpleEmail.setAuthenticator(new DefaultAuthenticator("ifpbpod@gmail.com", "rmi12345"));
            simpleEmail.setHostName("smtp.gmail.com");
            simpleEmail.setFrom("ifpbpod@gmail.com");
            simpleEmail.setSubject(email.getAssunto());

            simpleEmail.setMsg(email.getMensagem());

            List<InternetAddress> emailsAddress = new ArrayList<>();
            for (Pessoa pessoa : email.getDestinatarios()) {
                emailsAddress.add(new InternetAddress(pessoa.getEmail()));
            }
            simpleEmail.setTo(emailsAddress);
            simpleEmail.setDebug(true);
            simpleEmail.send();
        } catch (AddressException | EmailException ex) {
            resultado = "Erro : " + ex.getMessage();
        }
        return resultado;
    }

    @Override
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

    public void salvaEmail(Email email) throws RemoteException {
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

    @Override
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
        System.out.println(listaDePessoas.size());
        return listaDePessoas;
    }
}
