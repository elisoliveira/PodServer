/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.edu.br.sixpex.servidor.servicos;

import br.edu.ifpb.emailsharedpod.Email;
import br.edu.ifpb.emailsharedpod.Fachada;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elisiany
 */
public class EmailTask extends TimerTask {

    private final SisPexDao sisPexDao = new SisPexDao();

    @Override
    public void run() {
        
        System.out.println("[ Aguarde, estamos verificando se existe alguma mensagem pendente.]");

        List<Email> menssages = sisPexDao.listaEmailsNaoEnviados();

        if (!menssages.isEmpty()) {
            Registry registry;
            for (Email email : menssages) {
                try {
                    long tempoInicial = System.currentTimeMillis();
                    registry = LocateRegistry.getRegistry("localhost", 10999);
                    Fachada service = (Fachada) registry.lookup("fachada");

                    byte[] request = new byte[1024];

                    request[0] = 'J';
                    request[1] = 'A';
                    request[2] = 'C';
                    request[3] = 'A';
 
                    Long tempoDaOperacao = service.latencia(request).longValue();

                    Long tempoFinal = System.currentTimeMillis();
                    Long tempoTotal = tempoFinal - tempoInicial;

                    float latencia = tempoTotal - tempoDaOperacao;
                    System.out.println("LATÊNCIA: " + latencia);

                    float banda = (1024 / (latencia / 2)) * 1000;
                    System.out.println("BANDA: " + banda);
                   
                    email.setMensagem(email.getMensagem() + "\nLargura da banda: " + String.valueOf(banda));

                    System.out.println(email.toString());
                    String resultado = service.enviaEmail(email);
                    if (resultado != null) {
                        sisPexDao.atualizaEmail(email);

                    }
                } catch (RemoteException | NotBoundException ex) {
                    Logger.getLogger(EmailTask.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
