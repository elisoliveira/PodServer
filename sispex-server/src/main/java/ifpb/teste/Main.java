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
public class Main {

    public static void main(String[] args) {
        try {
            FachadaService s = new FachadaService();

            Pessoa p = new Pessoa();

            p.setNome("Elisiany");
            p.setEmail("oelisiany@gmail.com");
            s.salvar(p);
            p.setNome("Izabel");
            p.setEmail("ads.izabel@gmail.com");
            s.salvar(p);
            p.setNome("Luciana");
            p.setEmail("luciana.gadelhaa@gmail.com");
            s.salvar(p);
            p.setNome("Izaquiel");
            p.setEmail("zefcruzbs@gmail.com");
            s.salvar(p);
            p.setNome("Fernando");
            p.setEmail("fernandodof@gmail.com");
            s.salvar(p);
            p.setNome("Kelson");
            p.setEmail("kelsonsd@gmail.com");
            s.salvar(p);
            p.setNome("Wagner");
            p.setEmail("wagner.abreucz@gmail.com");
            s.salvar(p);
            p.setNome("Filipe");
            p.setEmail("filipegermano89@gmail.com");
            s.salvar(p);
            p.setNome("Marciel");
            p.setEmail("marcielmj@gmail.com");
            s.salvar(p);
            p.setNome("Magdiel");
            p.setEmail("magdiel.ildefonso@gmail.com");
            s.salvar(p);
            p.setNome("Joel");
            p.setEmail("joelanio@gmail.com");
            s.salvar(p);

        } catch (RemoteException ex) {
            Logger.getLogger(FachadaService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
