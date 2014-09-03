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


    public String enviaEmail(Email email) throws RemoteException {
//        String resultado = "Emails enviados com sucesso!";
//        try {
//            SimpleEmail simpleEmail = new SimpleEmail();
//            simpleEmail.setSmtpPort(587);
//            simpleEmail.setTLS(true);
//            simpleEmail.setAuthenticator(new DefaultAuthenticator("minhapesquisapraticas@gmail.com", "projetopraticas123"));
//            simpleEmail.setHostName("smtp.gmail.com");
//            simpleEmail.setFrom("minhapesquisapraticas@gmail.com");
//            simpleEmail.setSubject("[SISPEX - Elisiany Oliveira]");
//
//            simpleEmail.setMsg(textoDaMensagem);
//
//            List<InternetAddress> emailsAddress = new ArrayList<>();
//            for (String email : emails) {
//                emailsAddress.add(new InternetAddress(email));
//            }
//            simpleEmail.setTo(emailsAddress);
//            simpleEmail.setDebug(true);
//            simpleEmail.send();
//        } catch (EmailException | AddressException ex) {
//            resultado = ex.getMessage();
//        }
//        return resultado;
        return null;
    }

    public void salvar(Pessoa pessoa) throws RemoteException {
        try {
            String sql = "insert into pessoa (nome, email) values (?, ?)";
            Connection con = ConnectionFactory.getConexao();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getEmail());
            
        } catch (SQLException ex) {
            Logger.getLogger(FachadaService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Pessoa> listaPessoas() throws RemoteException {
        List<Pessoa> listaDePessoas = new ArrayList<Pessoa>();
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

        } catch (SQLException ex) {
            Logger.getLogger(FachadaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(listaDePessoas.size());
        return listaDePessoas;
    }

    public static void main(String[] args) {

        FachadaService s = new FachadaService();
        try {
            s.listaPessoas();

        } catch (RemoteException ex) {
            Logger.getLogger(FachadaService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
