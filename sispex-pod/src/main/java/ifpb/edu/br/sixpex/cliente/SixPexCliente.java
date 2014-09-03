/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.edu.br.sixpex.cliente;

import ifpb.edu.br.sixpex.servidor.interfaces.EmailRemote;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

/**
 *
 * @author Elisiany
 */
public class SixPexCliente {

    private static Registry registry;

    static {
        try {
            registry = LocateRegistry.getRegistry("localhost", 1099);
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "SixPex", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static EmailRemote lookupEmailRemote() throws NotBoundException, RemoteException {
        EmailRemote emailRemote = null;
        return (EmailRemote) registry.lookup("emailRemote");
    }

}
