/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.edu.br.sixpex.servidor;

import br.edu.ifpb.emailsharedpod.Fachada;
import ifpb.edu.br.sixpex.servidor.servicos.FachadaService;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Elisiany
 */
public class SisPexServidor {

    public static void main(String[] args) {

        try {
            Fachada fachada  = new FachadaService();
            Fachada fachadaStub = (Fachada) UnicastRemoteObject.exportObject(fachada, 10999);
            Registry registry = LocateRegistry.createRegistry(10999);
            registry.rebind("fachada", fachadaStub);
            System.out.println("Servidor Iniciado...");

        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
