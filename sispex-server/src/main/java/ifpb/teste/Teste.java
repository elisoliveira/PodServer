/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.teste;

import br.edu.ifpb.emailsharedpod.Pessoa;
import ifpb.edu.br.sixpex.servidor.servicos.FachadaService;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elisiany
 */
public class Teste {

    public static void main(String[] args) {
        try {
            FachadaService s = new FachadaService();

            Pessoa p = new Pessoa();
            p.setNome("Izabel");
            p.setEmail("ads.izabel@gmail.com");
            s.salvar(p);

        } catch (RemoteException ex) {
            Logger.getLogger(FachadaService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
