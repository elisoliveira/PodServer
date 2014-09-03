/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.edu.br.sixpex.servidor;

import ifpb.edu.br.sixpex.servidor.interfaces.EmailRemote;
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

//        try {

//            EmailRemote emailRemote = new FachadaService();
//            EmailRemote emailStub = (EmailRemote) UnicastRemoteObject.exportObject(emailRemote, 1099);
//            Registry registry = LocateRegistry.createRegistry(1099);
//            registry.rebind("emailRemote", emailStub);
//            System.out.println("Servidor Iniciado...");

//        } catch (RemoteException ex) {
//            System.out.println(ex.getMessage());
//            ex.printStackTrace();
//        }
    }
}
