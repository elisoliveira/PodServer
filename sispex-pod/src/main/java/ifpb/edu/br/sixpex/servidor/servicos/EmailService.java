/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.edu.br.sixpex.servidor.servicos;

import ifpb.edu.br.sixpex.servidor.interfaces.EmailRemote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class EmailService implements EmailRemote {

    @Override
    public String enviaEmail(List<String> emails, String textoDaMensagem) throws RemoteException {
        String resultado = "Emails enviados com sucesso!";
        try {
            SimpleEmail simpleEmail = new SimpleEmail();
            simpleEmail.setSmtpPort(587);
            simpleEmail.setTLS(true);
            simpleEmail.setAuthenticator(new DefaultAuthenticator("minhapesquisapraticas@gmail.com", "projetopraticas123"));
            simpleEmail.setHostName("smtp.gmail.com");
            simpleEmail.setFrom("minhapesquisapraticas@gmail.com");
            simpleEmail.setSubject("[SISPEX - Elisiany Oliveira]");

            simpleEmail.setMsg(textoDaMensagem);

            List<InternetAddress> emailsAddress = new ArrayList<>();
            for (String email : emails) {
                emailsAddress.add(new InternetAddress(email));
            }
            simpleEmail.setTo(emailsAddress);
            simpleEmail.setDebug(true);
            simpleEmail.send();
        } catch (EmailException | AddressException ex) {
            resultado = ex.getMessage();
        }
        return resultado;
    }

    @Override
    public List<String> getEmails() throws RemoteException {
        List<String> emails = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/minhapesquisa", "postgres", "123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select email from aluno");
            while(resultSet.next()){
                emails.add(resultSet.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emails;
    }
}
