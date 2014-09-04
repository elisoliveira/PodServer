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
import java.util.ArrayList;
import java.util.List;
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

    private SisPexDao sisPexDao = new SisPexDao();

    @Override
    public String enviaEmail(Email email) throws RemoteException {
        if (email.getId() != null) {
            return confirmEmail(email);
        } else {
            sisPexDao.salvaEmails(email);
        }
        return null;
    }

    private String confirmEmail(Email email) {
        String resultado = null;
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
            resultado = "Email(s) enviado(s) com sucesso!";
        } catch (AddressException | EmailException ex) {
        }
        return resultado;
    }

    public void salvaEmails(Email email) throws RemoteException {
        sisPexDao.salvaEmails(email);
    }

    @Override
    public List<Pessoa> listaPessoas() throws RemoteException {
        return sisPexDao.listaPessoas();
    }

    @Override
    public void salvar(Pessoa pessoa) throws RemoteException {
        sisPexDao.salvar(pessoa);
    }

    @Override
    public Long latencia(byte[] array) {
        long tempo1 = System.currentTimeMillis();
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            return null;
        }
        long tempo2 = System.currentTimeMillis();
        return tempo2 - tempo1;
    }

}
